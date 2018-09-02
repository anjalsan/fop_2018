package com.fpl.fop.fop;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import com.fpl.fop.fop.common.TestDataFactory;
import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.data.remote.FOPService;
import com.fpl.fop.fop.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private FOPService mockFOPService;

    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager(mockFOPService);
    }

    @Test
    public void getPokemonListCompletesAndEmitsPokemonList() {
        List<BfwTeam> bfwTeamList = TestDataFactory.makeNamedResourceList(5);
        PokemonListResponse pokemonListResponse = new PokemonListResponse();
        pokemonListResponse.results = bfwTeamList;

        when(mockFOPService.getPokemonList(anyInt()))
                .thenReturn(Single.just(pokemonListResponse));

        dataManager
                .getPokemonList(10)
                .test()
                .assertComplete()
                .assertValue(TestDataFactory.makePokemonNameList(bfwTeamList));
    }

    @Test
    public void getPokemonCompletesAndEmitsPokemon() {
        String name = "charmander";
        Pokemon pokemon = TestDataFactory.makePokemon(name);
        when(mockFOPService.getPokemon(anyString())).thenReturn(Single.just(pokemon));

        dataManager.getPokemon(name).test().assertComplete().assertValue(pokemon);
    }
}
