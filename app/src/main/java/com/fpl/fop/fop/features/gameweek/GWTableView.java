package com.fpl.fop.fop.features.gameweek;

import java.util.List;

import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.base.MvpView;

public interface GWTableView extends MvpView {

    void showPokemon(List<BfwTeam> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);
}
