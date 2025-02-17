package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.steps.AppBarStep;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoveIsAllTest extends DataGenerator {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void loginAuth() {
        try {
            MainStep.checkNewsTitle();
        } catch (androidx.test.espresso.PerformException e) {
            AuthorizationStep.loginFieldInput(validLogin);
            AuthorizationStep.passwordFieldInput(validPassword);
            AuthorizationStep.clickLoginBtn();
        }
        MainStep.checkNewsTitle();
        AppBarStep.clickLoveIsAllBtn();
        AppBarStep.checkLoveIsAllBtn();
    }


    @Test
    @Story("Разворачивание описания цитат")
    public void ShouldOpenDescription() {
        waitUntilElement(R.id.our_mission_item_list_recycler_view);
        LoveIsAllPage.clickOnQuote(num);
        //Assert
        waitUntilElement(R.id.our_mission_item_description_text_view);
        LoveIsAllPage.openDiscription(num).check(matches(isDisplayed()));
    }

    @Test
    @Story("Сворачивание описания цитат")
    public void ShouldCloseDescription() {
        ShouldOpenDescription();

        waitUntilElement(R.id.our_mission_item_list_recycler_view);
        LoveIsAllPage.clickOnQuote(num);
        //Assert
        waitUntilElement(R.id.our_mission_item_description_text_view);
        LoveIsAllPage.openDiscription(num).check(matches(not(isDisplayed())));
    }

}
