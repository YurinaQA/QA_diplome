package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.RecyclerViewMatcher;

public class LoveIsAllPage {

    public ViewInteraction getCardAt(int position) {
        return onView(new RecyclerViewMatcher(R.id.our_mission_item_list_recycler_view)
                .atPositionOnView(position, R.id.our_mission_item_material_card_view));
    }

    public ViewInteraction getDescriptionTextAt(int position) {
        return onView(new RecyclerViewMatcher(R.id.our_mission_item_list_recycler_view)
                .atPositionOnView(position, R.id.our_mission_item_description_text_view));
    }

    public void clickOnQuote(int position) {
        getCardAt(position).perform(click());
    }

    public ViewInteraction getLoveIsAllTitle() {
        return onView(withId(R.id.our_mission_title_text_view));
    }
}
