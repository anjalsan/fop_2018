package com.fpl.fop.fop;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.fpl.fop.fop.data.DataManager;
import com.singhajit.sherlock.core.Sherlock;
import com.squareup.leakcanary.LeakCanary;
import com.tspoon.traceur.Traceur;

import com.fpl.fop.fop.injection.component.AppComponent;
import com.fpl.fop.fop.injection.component.DaggerAppComponent;
import com.fpl.fop.fop.injection.module.AppModule;
import com.fpl.fop.fop.injection.module.NetworkModule;
import timber.log.Timber;

public class MvpStarterApplication extends MultiDexApplication {

    private AppComponent appComponent;
    public static DataManager dataManager = null;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();
        }
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule(this, BuildConfig.POKEAPI_API_URL))
                    .appModule(new AppModule(this))
                    .build();

            dataManager = appComponent.apiManager();
        }
        return appComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
