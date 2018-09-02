package com.fpl.fop.fop;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fpl.fop.fop.common.TestDataFactory;
import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.features.detail.BFWPresenter;
import com.fpl.fop.fop.features.detail.DetailMvpView;
import com.fpl.fop.fop.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class BFWPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    DetailMvpView mockDetailMvpView;
    @Mock
    DataManager mockDataManager;
    private BFWPresenter BFWPresenter;

    @Before
    public void setUp() {
        BFWPresenter = new BFWPresenter(mockDataManager);
        BFWPresenter.attachView(mockDetailMvpView);
    }

    @After
    public void tearDown() {
        BFWPresenter.detachView();
    }

    @Test
    public void getPokemonDetailReturnsPokemon() throws Exception {
        Pokemon pokemon = TestDataFactory.makePokemon("id");
        when(mockDataManager.getPokemon(anyString())).thenReturn(Single.just(pokemon));

        BFWPresenter.getPokemon(anyString());

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showPokemon(pokemon);
        verify(mockDetailMvpView, never()).showError(any(Throwable.class));
    }

    @Test
    public void getPokemonDetailReturnsError() throws Exception {
        when(mockDataManager.getPokemon("id")).thenReturn(Single.error(new RuntimeException()));

        BFWPresenter.getPokemon("id");

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showError(any(Throwable.class));
        verify(mockDetailMvpView, never()).showPokemon(any(Pokemon.class));
    }
}
