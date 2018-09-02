package com.fpl.fop.fop.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.injection.ApplicationContext;
import com.fpl.fop.fop.injection.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
