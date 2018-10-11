package com.fpl.fop.fop.injection.component;

import dagger.Subcomponent;
import com.fpl.fop.fop.features.gameweek.GWTableActivity;
import com.fpl.fop.fop.features.home.HomeActivity;
import com.fpl.fop.fop.features.leaguetable.LeagueTableActivity;
import com.fpl.fop.fop.features.tally.TallyActivity;
import com.fpl.fop.fop.features.team.TeamActivity;
import com.fpl.fop.fop.injection.PerActivity;
import com.fpl.fop.fop.injection.module.ActivityModule;
import com.fpl.fop.fop.mvrx.TallyDetailsActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(HomeActivity HomeActivity);

    void inject(TeamActivity TeamActivity);

    void inject(GWTableActivity GWTableActivity);

    void inject(LeagueTableActivity LeagueTableActivity);

    void inject(TallyActivity TallyActivity);

    void inject(TallyDetailsActivity TallyDetailsActivity);
}
