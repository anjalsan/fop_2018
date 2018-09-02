package com.fpl.fop.fop;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import com.fpl.fop.fop.common.TestComponentRule;
import com.fpl.fop.fop.common.TestDataFactory;
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.gameweek.GWTableActivity;
import com.fpl.fop.fop.util.ErrorTestUtil;
import io.reactivex.Single;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class TallyActivityTest {

    private final TestComponentRule componentRule =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<GWTableActivity> mainActivityTestRule =
            new ActivityTestRule<>(GWTableActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(componentRule).around(mainActivityTestRule);

    @Test
    public void checkPokemonsDisplay() {
        List<BfwTeam> bfwTeamList = TestDataFactory.makeNamedResourceList(5);
        List<String> pokemonList = TestDataFactory.makePokemonNameList(bfwTeamList);
        stubDataManagerGetPokemonList(Single.just(pokemonList));
        mainActivityTestRule.launchActivity(null);

        for (BfwTeam pokemonName : bfwTeamList) {
            onView(withText(pokemonName.name)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void clickingPokemonLaunchesDetailActivity() {
        List<BfwTeam> bfwTeamList = TestDataFactory.makeNamedResourceList(5);
        List<String> pokemonList = TestDataFactory.makePokemonNameList(bfwTeamList);
        stubDataManagerGetPokemonList(Single.just(pokemonList));
        stubDataManagerGetPokemon(Single.just(TestDataFactory.makePokemon("id")));
        mainActivityTestRule.launchActivity(null);

        onView(withText(pokemonList.get(0))).perform(click());

        onView(withId(R.id.image_pokemon)).check(matches(isDisplayed()));
    }

    @Test
    public void checkErrorViewDisplays() {
        stubDataManagerGetPokemonList(Single.error(new RuntimeException()));
        mainActivityTestRule.launchActivity(null);
        ErrorTestUtil.checkErrorViewsDisplay();
    }

    public void stubDataManagerGetPokemonList(Single<List<String>> single) {
        when(componentRule.getMockApiManager().getPokemonList(anyInt())).thenReturn(single);
    }

    public void stubDataManagerGetPokemon(Single<Pokemon> single) {
        when(componentRule.getMockApiManager().getPokemon(anyString())).thenReturn(single);
    }
}
