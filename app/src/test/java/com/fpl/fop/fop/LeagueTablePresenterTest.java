package com.fpl.fop.fop;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import com.fpl.fop.fop.common.TestDataFactory;
import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.features.gameweek.GWTableView;
import com.fpl.fop.fop.features.gameweek.GWTablePresenter;
import com.fpl.fop.fop.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTablePresenterTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    GWTableView mockGWTableView;
    @Mock
    DataManager mockDataManager;
    private GWTablePresenter mainPresenter;

    @Before
    public void setUp() {
        mainPresenter = new GWTablePresenter(mockDataManager);
        mainPresenter.attachView(mockGWTableView);
    }

    @After
    public void tearDown() {
        mainPresenter.detachView();
    }

    @Test
    public void getPokemonReturnsPokemonNames() throws Exception {
        List<String> pokemonList = TestDataFactory.makePokemonNamesList(10);
        when(mockDataManager.getPokemonList(10)).thenReturn(Single.just(pokemonList));

        mainPresenter.getPokemon(10);

        verify(mockGWTableView, times(2)).showProgress(anyBoolean());
        verify(mockGWTableView).showPokemon(pokemonList);
        verify(mockGWTableView, never()).showError(any(Throwable.class));
    }

    @Test
    public void getPokemonReturnsError() throws Exception {
        when(mockDataManager.getPokemonList(10)).thenReturn(Single.error(new RuntimeException()));

        mainPresenter.getPokemon(10);

        verify(mockGWTableView, times(2)).showProgress(anyBoolean());
        verify(mockGWTableView).showError(any(Throwable.class));
        verify(mockGWTableView, never()).showPokemon(ArgumentMatchers.anyList());
    }
}
