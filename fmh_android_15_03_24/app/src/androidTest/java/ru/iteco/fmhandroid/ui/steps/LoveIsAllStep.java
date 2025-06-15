package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.WaitId;
import ru.iteco.fmhandroid.ui.pages.LoveIsAllPage;

public class LoveIsAllStep {

    private final LoveIsAllPage loveIsAllPage = new LoveIsAllPage();

    public void clickOnQuote(int num) {
        WaitId.waitUntilElement(R.id.our_mission_item_list_recycler_view);
        loveIsAllPage.clickOnQuote(num);
    }

    public void checkDescriptionIsDisplayed(int num) {
        WaitId.waitUntilElement(R.id.our_mission_item_description_text_view);
        loveIsAllPage.getDescriptionTextAt(num).check(matches(isDisplayed()));
    }

    public void checkDescriptionIsNotDisplayed(int num) {
        WaitId.waitUntilElement(R.id.our_mission_item_description_text_view);
        loveIsAllPage.getDescriptionTextAt(num).check(matches(not(isDisplayed())));
    }

    public void checkTitleIsDisplayed() {
        loveIsAllPage.getLoveIsAllTitle().check(matches(isDisplayed()));
    }
}
