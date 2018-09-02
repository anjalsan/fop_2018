package com.fpl.fop.fop.features.leaguetable;

import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.base.MvpView;

import java.util.List;

public interface LeagueTableView extends MvpView {

    void showPokemon(List<BfwTeam> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);
}
