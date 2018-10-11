package com.fpl.fop.fop.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fpl.fop.fop.data.model.response.PlayerTallyItem;
import com.fpl.fop.fop.data.model.response.TallyItem;
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.data.remote.FOPService;

import io.reactivex.Single;

/**
 */
@Singleton
public class DataManager {

    private FOPService FOPService;

    @Inject
    public DataManager(FOPService FOPService) {
        this.FOPService = FOPService;
    }

    public Single<List<BfwTeam>> getPokemonList(int limit) {
        return FOPService
                .getPokemonList(limit);
    }

    public Single<List<TallyItem>> getVegList(String url) {
        return FOPService
                .getTallyList(url);
    }

    public Single<List<PlayerTallyItem>> getFullVegList(String url) {
        return FOPService
                .getFullTallyList(url);
    }
}
