package ru.iteco.fmhandroid.ui.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    // Данные для тестирования страницы "Authorization"
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String invalidInput = "test";
    public String capitalLogin = "LOGIN2";
    public String capitalPassword = "PASSWORD2";

    //Данные для тестирования AppBar
    // ____________________________________________________________
    //Данные для тестирования страницы "Main"
    public static String allNewsTitle = "ALL NEWS";
    //____________________________________________________________
    //Данные для тестирования страницы "News"
    public static String newsPageTitle = "News";
    public static String category_1 = "День рождения";
    public static String category_2 = "Нужна помощь";
    public static String filterNewsTitle = "Filter news";
    public static int number = 2;
    public static String wrongText = "Деньги";
    public static String controlPanelTitle = "Control panel";
    public static String textTitle = "Test";
    public static String statusActive = "ACTIVE";
    public static String statusNotActive = "NOT ACTIVE";
    public static String newNews = "Текст новой новости";

    public static String setData(String data) {
        return data;
    }

    public static String futureDate(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    public static String futureTimeMinute(int input) {
        String pattern = "HH:mm";
        String time = LocalTime.now().plusMinutes(input).format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }

    public static String addTime(int hours, int minutes) {
        return hours + ":" + minutes;
    }


    //____________________________________________________________
    //Данные для тестирования страницы "About"
    public static String versionText = "Version:";
    public static String privacyPolicy = "Privacy Policy:";
    public static String termsOfUse = "Terms of use:";
    //____________________________________________________________
    //Данные для тестирования страницы "Love is All"
    public static String loveTitle = "Love is all";
    public static int num = 2;
}
