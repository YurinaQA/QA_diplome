package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.action.ViewActions;

import ru.iteco.fmhandroid.R;

public class NewsPage {

    public ViewInteraction newsPageTitle() {
        return onView(allOf(withId(R.id.all_news_text_view), withText("ALL NEWS")));
    }

    public ViewInteraction refreshBtn() {
        return onView(withId(R.id.news_list_swipe_refresh));
    }

    public ViewInteraction newsList() {
        return onView(withId(R.id.news_list_recycler_view));
    }

    public ViewInteraction newsBox() {
        return onView(withId(R.id.news_item_material_card_view));
    }

    public ViewInteraction newCPTitleNews() {
        return onView(withId(R.id.news_item_title_text_view));
    }

    public ViewInteraction cpDeleteBtn() {
        return onView(withId(R.id.delete_news_item_image_view));
    }

    public ViewInteraction cpDialogWindow() {
        return onView(withText("Are you sure you want to permanently delete the document?"));
    }

    public ViewInteraction cpDialogWinOKBtn() {
        return onView(withId(android.R.id.button1));
    }

    public ViewInteraction filterNewsSection() {
        return onView(withId(R.id.filter_news_material_button));
    }

    public ViewInteraction filterNewsSectionTitle() {
        return onView(withText("Filter news"));
    }

    public ViewInteraction openCategoryList() {
        return onView(withId(R.id.news_item_category_text_input_layout));
    }

    public ViewInteraction filterBtn() {
        return onView(withId(R.id.filter_button));
    }

    public ViewInteraction cancelBtnFilter() {
        return onView(withId(R.id.cancel_button));
    }

    public ViewInteraction notNewsYet() {
        return onView(withText("There is nothing here yet…"));
    }

    public ViewInteraction controlPanel() {
        return onView(withId(R.id.edit_news_material_button));
    }

    public ViewInteraction controlPanelTitle() {
        return onView(withText("Control panel"));
    }

    public ViewInteraction editingBtn() {
        return onView(withId(R.id.edit_news_item_image_view));
    }

    public ViewInteraction editingSectionTitle() {
        return onView(withText("Editing"));
    }

    public ViewInteraction creatingNewsBtn() {
        return onView(withId(R.id.add_news_image_view));
    }

    public ViewInteraction creatingNewsTitle() {
        return onView(withText("Creating"));
    }

    public ViewInteraction enSaveBtn() {
        return onView(withId(R.id.save_button));
    }

    public ViewInteraction enCancelBtn() {
        return onView(withId(R.id.cancel_button));
    }

    public ViewInteraction message() {
        return onView(withId(android.R.id.message));
    }

    public ViewInteraction okMessage() {
        return onView(withId(android.R.id.button1));
    }

    public ViewInteraction newsCategory() {
        return onView(withId(R.id.news_item_category_text_view));
    }

    public ViewInteraction newsDescription() {
        return onView(withId(R.id.news_item_description_text_view));
    }

    public ViewInteraction newsDate() {
        return onView(withId(R.id.news_item_date_text_view));
    }

    public ViewInteraction expandNewsBtn() {
        return onView(withId(R.id.view_news_item_image_view));
    }

    public ViewInteraction newsCategoryIcon() {
        return onView(withId(R.id.category_icon_image_view));
    }

    public ViewInteraction customCategoryInput() {
        return onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    }

    public ViewInteraction filteredNewsList() {
        return onView(withId(R.id.news_list_recycler_view));
    }

    // Новый метод выбора категории из выпадающего списка
    public void selectCategoryFromList(String category) {
        onView(withText(category))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(ViewActions.click());
    }
}
