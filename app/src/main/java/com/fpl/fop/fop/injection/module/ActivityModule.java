package com.fpl.fop.fop.injection.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.team.TeamActivity;
import com.fpl.fop.fop.injection.ActivityContext;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

//    @Provides
//    @ActivityContext
//    @PresenterParam("extra_team")
//    public BfwTeam lifecycle(TeamActivity activity) {
//        return (BfwTeam) activity.getIntent().getBundleExtra(TeamActivity.EXTRA_TEAM).getSerializable(TeamActivity.EXTRA_TEAM);
//    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }
}
