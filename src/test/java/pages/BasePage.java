package pages;

import runners.SelenideRunner;

public class BasePage extends SelenideRunner {
    public MainPage mainPage = new MainPage();
    public CatalogPage catalogPage = new CatalogPage();
    public ServicePage servicePage = new ServicePage();
    public SubServicePage subServicePage = new SubServicePage();
}
