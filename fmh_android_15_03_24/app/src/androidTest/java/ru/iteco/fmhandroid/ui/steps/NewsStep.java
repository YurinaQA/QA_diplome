package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPDeleteBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPDialogWinOKBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPDialogWindow;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPNewsCard;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPNewsCardChecking;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPNewsCardTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CPTitleNews;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CancelBtnFiler;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CategoryList;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanel;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanelTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CreatingNewsBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.CreatingNewsTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.DateEN;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.DateField;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.DiscriptionEN;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.DiscriptionFieldEN;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.ENCancelBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.ENCategoryField;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.ENSaveBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.EditingBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.EditingSectionTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.FilterNewsSection;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.FilterNewsSectionitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.NewCPTitleNews;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.NewsPageTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.OpenCategoryList;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.RefreshBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.Switcher;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.TimeField;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.TitleField;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.categoryNews;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.checkStatusText;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.filterBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.notNewsYet;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.openDiscription;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.textInputEditText;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

public class NewsStep extends DataGenerator {

    //Проверка отображения заголовка страницы "News"
    public static void checkNewsPageTitle() {
        waitUntilElement(R.id.container_list_news_include);
        NewsPageTitle.check(matches(withText("News")));
    }

    //Открытие новости
    public static void openNewsBox(int number) {
        waitUntilElement(R.id.news_list_recycler_view);
        NewsPage.NewsBox(number);
    }

    //Проверка отображения описания новости
    public static void checkNewsBox(int number) {
        waitUntilElement(R.id.news_item_description_text_view);
        NewsPage.openDiscription(number).check(matches(isDisplayed()));
    }

    //Обновление страницы кнопкой "Refrash"
    public static void clickRefrashBtn() {
        waitUntilElement(R.id.news_retry_material_button);
        RefreshBtn.perform(click());
    }

    //_________________________________________________
    //Переход в раздел "Filter news"
    public static void goToFilterSection() {
        waitUntilElement(R.id.filter_news_material_button);
        FilterNewsSection.perform(click());
        waitUntilElement(R.id.filter_news_title_text_view);
        FilterNewsSectionitle.check(matches(withText(filterNewsTitle)));
    }

    //Выбор категории из выпадающего списка
    public static void selectCategoryFiler(String category) {
        waitUntilElement(R.id.news_item_category_text_auto_complete_text_view);
        OpenCategoryList.perform(click(), closeSoftKeyboard());
        waitUntilElement(R.id.news_item_category_text_auto_complete_text_view);
        NewsPage.SelectCategoryFromList(category);
    }

    //Нажатие кнопки "Filter"
    public static void clickFilterBtn() {
        waitUntilElement(R.id.filter_button);
        filterBtn.perform(click());

    }

    //Проверка отображения категории в карточки новости
    public static void checkFilter() {
        waitUntilElement(R.id.container_list_news_include);
        waitUntilElement(R.id.news_item_category_text_view);
        categoryNews.check(matches(isEnabled()));
    }

    //Исключение если еще нет новостей
    public static void thereIsntNews() {
        waitUntilElement(R.id.empty_news_list_text_view);
        notNewsYet.check(matches(isDisplayed()));
    }

    //Нажатие на поле категории
    public static void clickOnCategory() {
        waitUntilElement(R.id.news_item_category_text_auto_complete_text_view);
        CategoryList.perform(click());
    }

    //Ручной ввод в поле категории
    public static void inputText(String partText) {
        waitUntilElement(R.id.news_item_category_text_auto_complete_text_view);
        CategoryList.perform(replaceText(partText));
    }

    //Нажатие кнопки "Cancel" в "Filter news"
    public static void clickCancelFilter() {
        waitUntilElement(R.id.cancel_button);
        CancelBtnFiler.perform(click());
    }

    //________________________________________________
    //Открытие раздела "Control panel"
    public static void openControlPanelSection() {
        waitUntilElement(R.id.edit_news_material_button);
        ControlPanel.perform(click());
    }

    //Проверка открытия раздела "Control panel"
    public static void checkControlPanelSectionTitle(String title) {
        waitUntilElement(title);
        ControlPanelTitle.check(matches(withText(title)));
    }

    //Проверка наличия карточек новостей в разделе "Control panel"
    public static void checkCPNewsCard() {
        waitUntilElement(R.id.news_item_material_card_view);
        CPNewsCard.check(matches(isDisplayed()));
    }

    //Удаление новости
    public static void CPDeleteNews() {
        waitUntilElement(R.id.delete_news_item_image_view);
        CPNewsCardTitle();
        CPDeleteBtn.perform(click());
        CPDialogWindow.check(matches(isDisplayed()));
        CPDialogWinOKBtn.perform(click());
    }

    //Проверка удаления карточки
    public static void checkDeleteCard() {
        CPNewsCardChecking.check(matches(not(withText(CPNewsCardTitle()))));
    }

    //Открытие раздела "Editing News"
    public static void openEditingSection() {
        EditingBtn.perform(click());
    }

    //Проверка открытия раздела "Editing News"
    public static void checkEditingSectionTitle() {
        waitUntilElement(R.id.custom_app_bar_title_text_view);
        EditingSectionTitle.check(matches(isDisplayed()));
    }

    //Проверка открытия раздела "Editing News"
    public static void CategoryEN(String text) {
        waitUntilElement(R.id.news_item_category_text_auto_complete_text_view);
        ENCategoryField.perform(replaceText(text));
    }


    //Изменение заголовка новости
    public static void replaceTitleEN(String title) {
        TitleField.perform(click());
        TitleField.perform(replaceText(title), closeSoftKeyboard());
    }

    //Изменение заголовка новости
    public static void emptyTitleEN(String text) {
        textInputEditText.perform(click());
        textInputEditText.perform(clearText());
        textInputEditText.perform(replaceText(text), closeSoftKeyboard());
    }

    //проверка отображания нового заголовка  в карточке новости
    public static void checkTitleEN(String text) {
        waitUntilElement(R.id.news_item_title_text_view);
        CPTitleNews.check(matches(withText(text)));
    }

    //Удаление заголовка
    public static void emptyTitleEN() {
        TitleField.perform(click());
        TitleField.perform(replaceText(""), closeSoftKeyboard());
    }

    //Введение даты в поле
    public static void DateFieldSet(String date) {
        DateField.perform(replaceText(date));
    }

    //Проверка новой даты в карточке новости
    public static void checkDateEN(String date) {
        waitUntilElement(R.id.news_item_publication_date_text_view);
        DateEN.check(matches(withText(date)));
    }

    //Введение времени
    public static void TimeFieldSet(String time) {
        TimeField.perform(replaceText(time));
    }

    //Изменение описания
    public static void DiscriptionSet(String text) {
        waitUntilElement(R.id.news_item_description_text_input_edit_text);
        DiscriptionFieldEN.perform(replaceText(text));
    }

    //Проверка открытия описания
    public static void checkDiscription(String text) {
        waitUntilElement(R.id.news_list_recycler_view);
        openDiscription.perform(click());
        waitUntilElement(R.id.news_item_description_text_view);
        DiscriptionEN.check(matches(withText(text)));
    }

    //Изменение свитчера
    public static void SwitcherTurn() {
        waitUntilElement(R.id.switcher);
        Switcher.perform(click());
    }

    //Проверка изменения статуса
    public static void checkStatus(String status) {
        waitUntilElement(R.id.news_item_published_text_view);
        checkStatusText.check(matches(withText(status)));
    }


    //Нажатие кнопки Save
    public static void clickSaveBtn() {
        ENSaveBtn.perform(click());
    }

    //Нажатие кнопки Cancel
    public static void clickCancelBtn() {
        ENCancelBtn.perform(click());
    }

    //Согласие закрыть раздел
    public static void checkMessage() {
        waitUntilElement(android.R.id.message);
        CPDialogWinOKBtn.perform(click());
    }

    //Открытие раздела "Creating news"
    public static void openCreatingNews() {
        waitUntilElement(R.id.add_news_image_view);
        CreatingNewsBtn.perform(click());
    }

    //Заголовок раздела "Creating news"
    public static void checkCreatingNewsTitle() {
        waitUntilElement(R.id.custom_app_bar_title_text_view);
        CreatingNewsTitle.check(matches(withText("Creating")));
    }

    //Проверка заголовка новой и даты создания новости
    public static void checkNewCreatingNewsTitle(String text) {
        waitUntilElement(R.id.news_item_title_text_view);
        NewCPTitleNews(text);
    }


}
