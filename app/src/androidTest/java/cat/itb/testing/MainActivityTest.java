package cat.itb.testing;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaClickableAssertions.assertClickable;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public String USER_TO_BE_TYPED = "Username";
    public String PASS_TO_BE_TYPED = "Password";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void view_elements_are_displayed() {

        onView(withId(R.id.text)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).check(matches(isDisplayed()));

    }

    @Test
    public void view_elements_are_correct() {

        onView(withId(R.id.button)).check(matches(withText(R.string.next)));
        onView(withId(R.id.text)).check(matches(withText(R.string.text)));

    }

    @Test
    public void button_is_clickable() {

        //espresso
        onView(withId(R.id.button)).check(matches(isClickable()));
        //barista
        assertClickable(R.id.button);

        //onView(withId(R.id.button)).perform(click()).check(matches(withText(R.string.back)));

    }

    @Test
    public void login_form_behaviour() {

        onView(withId(R.id.username)).perform(typeText(USER_TO_BE_TYPED)).perform(pressBack());
        onView(withId(R.id.password)).perform(typeText(PASS_TO_BE_TYPED)).perform(pressBack());

        //onView(withId(R.id.button)).perform(click()).check(matches(withText(R.string.logged)));
    }

    @Test
    public void activity_changed() {

        Intents.init();
        onView(withId(R.id.button)).perform(click());
        intended(hasComponent(WelcomeActivity.class.getName()));
        Intents.release();

    }

    @Test
    public void go_back_acvitity(){

        Intents.init();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.buttonBack)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();

    }

    @LargeTest
    @Test
    public void large_test(){

        onView(withId(R.id.username)).perform(typeText(USER_TO_BE_TYPED)).perform(pressBack());

        onView(withId(R.id.password)).perform(typeText(PASS_TO_BE_TYPED)).perform(pressBack());

        Intents.init();
        onView(withId(R.id.button)).perform(click());

        intended(hasComponent(WelcomeActivity.class.getName()));

        onView(withId(R.id.welcomeText)).check(matches(withText("Welcome back " + USER_TO_BE_TYPED)));

        onView(withId(R.id.buttonBack)).perform(click());

        intended(hasComponent(MainActivity.class.getName()));

        onView(withId(R.id.username)).check(matches(withText("")));
        onView(withId(R.id.password)).check(matches(withText("")));

        Intents.release();

    }

}
