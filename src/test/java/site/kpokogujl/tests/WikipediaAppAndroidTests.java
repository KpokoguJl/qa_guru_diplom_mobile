package site.kpokogujl.tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;


public class WikipediaAppAndroidTests extends TestBase {
    @Test
    @Owner("allure8")
    @Feature("Wikipedia Mobile App Tests")
    @Story("Onboarding Screen Tests")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Добавление русского языка на первом экране")
    void addLanguageOnFirstScreen() {

        step("Кликаю добавить язык", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")).click());
        step("Кликаю добавить язык в меню языков", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).last().click());
        step("Выбираю Русский", () ->
                $(AppiumBy.xpath("//android.widget.TextView[@text='Русский']")).click());
        step("Проверяю, что в списке есть Русский", () ->
                $(AppiumBy.xpath("//android.widget.TextView[@text='Русский']"))
                        .shouldHave(attribute("text", "Русский")));
        step("Возвращаюсь назад", () ->
                $(AppiumBy.className("android.widget.ImageButton")).click());
        step("Проверяю, что в списке есть Русский", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/languagesList"))
                        .$$(AppiumBy.id("org.wikipedia.alpha:id/option_label")).shouldHave(size(2)));
    }

    @Test
    @Owner("allure8")
    @Feature("Wikipedia Mobile App Tests")
    @Story("Onboarding Screen Tests")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка текста на втором экране онбординга")
    void checkTextOnSecondScreen() {

        String textOnScreenTwo = "Dive down the Wikipedia rabbit hole with a constantly updating Explore feed. \n" +
                "Customize the feed to your interests – whether it’s learning about historical events On this day, or rolling the dice with Random.";
        step("Скролю на второй экран", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверяю текст на экране", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(attribute("text", "New ways to explore"));
            $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                    .shouldHave(attribute("text", textOnScreenTwo));
        });
    }

    @Test
    @Owner("allure8")
    @Feature("Wikipedia Mobile App Tests")
    @Story("Onboarding Screen Tests")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка текста на третьем экране")
    void checkTextOnThirdScreen() {

        String textOnScreenThree = "You can make reading lists from articles you want to read later, even when you’re offline. \n" +
                "Login to your Wikipedia account to sync your reading lists. Join Wikipedia";
        step("Скролю на третий экран", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверяю текст на экране", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(attribute("text", "Reading lists with sync"));
            $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                    .shouldHave(attribute("text", textOnScreenThree));
        });
    }

    @Test
    @Owner("allure8")
    @Feature("Wikipedia Mobile App Tests")
    @Story("Onboarding Screen Tests")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка свитча отправки статистики на четвертом экране")
    void checkTextOnFourthScreen() {

        String textOnScreenFour = "Help make the app better by letting us know how you use it. Data collected is anonymous. Learn more";

        step("Скролю на третий экран", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверяю текст на экране", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(attribute("text", "Send anonymous data"));
            $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave(attribute("text", textOnScreenFour));
        });
        step("Выключаю свитч отправки данных", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/switchView")).click());
        step("Проверяю, что свитч отправки данных выключен", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/switchView"))
                        .shouldHave(attribute("checked", "false")));
        step("Нажимаю GET STARTED", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click());
        step("Проверяю, что открывается главный экран", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar_wordmark")).shouldBe());
    }

    @Test
    @Owner("allure8")
    @Feature("Wikipedia Mobile App Tests")
    @Story("Search Tests")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка поиска")
    void searchTest() {

        step("Закрываю экран настроек", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Кликаю по элементу поиска", () ->
                $(AppiumBy.accessibilityId("Search Wikipedia")).click());
        step("Ввожу текст в поле поиска", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                        .setValue("BrowserStack"));
        step("Проверяю, что результатов поиска больше 0", () ->
                $$(AppiumBy.className("android.widget.TextView"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}
