package omise.charity.app.charity.charityDetail;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import omise.charity.app.R;
import omise.charity.app.charity.charityList.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static omise.charity.app.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CharityDetailActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

	@Before
	public void setup() {
		onData(anything()).inAdapterView(withId(R.id.main_activity_list_view)).atPosition(0).perform(click());
	}

	@Test
	public void areCharityInformationShownCorrectly() {
		onView(withId(R.id.charity_detail_name)).check(matches(withText("Habitat for Humanity")));
		onView(withId(R.id.charity_detail_logo)).check(matches(hasDrawable()));
	}

	@Test
	public void isNameErrorShown() {
		onView(withId(R.id.charity_detail_card_number_et)).perform(
				typeText("4111111111111111"));
		onView(withId(R.id.charity_detail_security_code_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_security_code_et)).perform(typeText("123"));
		onView(withId(R.id.charity_detail_amount_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_amount_et)).perform(typeText("1000"));

		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withId(R.id.charity_detail_full_name_et)).check(matches(
				hasErrorText("Enter a valid name")));
	}

	@Test
	public void isCreditCardErrorShown() {
		onView(withId(R.id.charity_detail_full_name_et)).perform(typeText("Nami"));
		onView(withId(R.id.charity_detail_security_code_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_security_code_et)).perform(typeText("123"));
		onView(withId(R.id.charity_detail_amount_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_amount_et)).perform(typeText("1000"));

		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withId(R.id.charity_detail_card_number_et)).check(matches(
				hasErrorText("Enter a valid card number")));
	}

	@Test
	public void isSecurityCodeErrorShown() {
		onView(withId(R.id.charity_detail_full_name_et)).perform(typeText("Nami"));
		onView(withId(R.id.charity_detail_card_number_et)).perform(
				typeText("4111111111111111"));
		onView(withId(R.id.charity_detail_amount_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_amount_et)).perform(typeText("1000"));

		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withId(R.id.charity_detail_security_code_et)).check(matches(
				hasErrorText("Enter a valid security code")));
	}

	@Test
	public void isAmountErrorShown() {
		onView(withId(R.id.charity_detail_full_name_et)).perform(typeText("Nami"));
		onView(withId(R.id.charity_detail_card_number_et)).perform(
				typeText("4111111111111111"));
		onView(withId(R.id.charity_detail_security_code_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_security_code_et)).perform(typeText("123"));

		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withId(R.id.charity_detail_amount_et)).check(matches(
				hasErrorText("Enter a valid Amount")));
	}

	@Test
	public void shouldShowFailedDonationError() {
		onView(withId(R.id.charity_detail_full_name_et)).perform(typeText("Nami"));
		onView(withId(R.id.charity_detail_card_number_et)).perform(
				typeText("4211111111111111"));
		onView(withId(R.id.charity_detail_security_code_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_security_code_et)).perform(typeText("123"));
		onView(withId(R.id.charity_detail_amount_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_amount_et)).perform(typeText("1000"));

		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withText("number is invalid")).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

	}


	@Test
	public void isDonationSuccessShown(){
		onView(withId(R.id.charity_detail_full_name_et)).perform(typeText("Nami"));
		onView(withId(R.id.charity_detail_card_number_et)).perform(
				typeText("4111111111111111"));
		onView(withId(R.id.charity_detail_security_code_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_security_code_et)).perform(typeText("123"));
		onView(withId(R.id.charity_detail_amount_et)).perform(scrollTo());
		onView(withId(R.id.charity_detail_amount_et)).perform(typeText("1000"));

		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withText("Thank you for your donation")).check(matches(isDisplayed()));
	}

	@Test
	public void isDonateButtonEnabledAfterPressed(){
		onView(withId(R.id.charity_detail_donate_button)).perform(scrollTo());
		onView(withId(R.id.charity_detail_donate_button)).perform(click());

		onView(withId(R.id.charity_detail_donate_button)).check(matches(isEnabled()));
	}
}