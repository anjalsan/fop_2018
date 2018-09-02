package com.fpl.fop.fop.features.tally;

import com.fpl.fop.fop.data.model.response.TallyItem;
import com.fpl.fop.fop.features.base.MvpView;

import java.util.List;

public interface TallyView extends MvpView {

    void showTallys(List<TallyItem> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);
}
