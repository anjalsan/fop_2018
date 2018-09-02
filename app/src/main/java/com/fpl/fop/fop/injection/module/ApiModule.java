package com.fpl.fop.fop.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.fpl.fop.fop.data.remote.FOPService;

import retrofit2.Retrofit;

/**
 */
@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    FOPService providePokemonApi(Retrofit retrofit) {
        return retrofit.create(FOPService.class);
    }
}
