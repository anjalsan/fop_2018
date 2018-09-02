package com.fpl.fop.fop.features.home;

import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.base.MvpView;

import java.util.List;

public interface HomeContractView extends MvpView {

    void clickGWLive();

    void clickTable();

    void clickOther();
}