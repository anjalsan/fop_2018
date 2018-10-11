package com.fpl.fop.fop.data.remote;

import com.fpl.fop.fop.data.model.response.PlayerTallyItem;
import com.fpl.fop.fop.data.model.response.TallyItem;
import com.fpl.fop.fop.data.model.response.BfwTeam;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface FOPService {

    @GET("bfw/api/teams/")
    Single<List<BfwTeam>> getPokemonList(@Query("limit") int limit);

    @GET
    Single<List<TallyItem>> getTallyList(@Url String url);

    @GET
    Single<List<PlayerTallyItem>> getFullTallyList(@Url String url);
}
