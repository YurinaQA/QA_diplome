package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.steps.LoveIsAllStep;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class LoveIsAllTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    private final LoveIsAllStep loveIsAllStep = new LoveIsAllStep();

    @Test
    public void testDescriptionVisibility() {
        int itemIndex = 0; // индекс тестируемого элемента в RecyclerView

        loveIsAllStep.clickOnQuote(itemIndex);
        loveIsAllStep.checkDescriptionIsDisplayed(itemIndex);

        loveIsAllStep.clickOnQuote(itemIndex);
        loveIsAllStep.checkDescriptionIsNotDisplayed(itemIndex);
    }

    @Test
    public void testTitleIsDisplayed() {
        loveIsAllStep.checkTitleIsDisplayed();  // <-- вызываем метод из LoveIsAllStep
    }
}
