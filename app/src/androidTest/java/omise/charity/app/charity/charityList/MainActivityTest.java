package omise.charity.app.charity.charityList;

import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import omise.charity.app.R;
import omise.charity.app.charity.charityDetail.CharityDetailActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void hasDataOnTheScreen() {
		onView(withText("Habitat for Humanity")).check(matches(isDisplayed()));
	}

	@Test
	public void itemClickNavigateAway() throws Exception {
		Intents.init();

		onData(anything()).inAdapterView(withId(R.id.main_activity_list_view)).atPosition(0).perform(click());

		intended(hasComponent(CharityDetailActivity.class.getName()));

		Intents.release();
	}

}