package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final SelenideElement username = $(byXpath("//div[@class='b-header-profile__shortName']/div"));
    private static final SelenideElement catalogLink = $(byXpath("//*[contains(text(), 'Каталог услуг')]"));

    public String getUsername() {
        return username.getText();
    }

    public void goToCatalog() {
        catalogLink.click();
    }
}
