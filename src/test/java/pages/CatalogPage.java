package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;

public class CatalogPage {
    private SelenideElement buttonLoadMore;

    public void loadMore() {
        try {
            while (true) {
                buttonLoadMore = $(byXpath("//*[@class=\"btn btn-lg btn-primary btn--tiles-more btn--pagination\"]"));
                buttonLoadMore.click();
            }
        }catch (Throwable e) { }
    }

    public List<String> getLinksCategories(){
        String link = "https://gosuslugi29.ru/pgu/categories/info.htm?id=";
        List<String> links = new ArrayList<>();
        List<SelenideElement> allCategories = getAllCategories();
        for (SelenideElement category : allCategories){
            links.add(link + category.getAttribute("data-objid"));
        }
        return links;
    }

    private List<SelenideElement> getAllCategories(){
        return $$(byXpath("//*[@class=\"t-modal-layout-item g-tile g-tile--3\"]"));
    }

    public Map<String, String> getLinksServices(){
        List<SelenideElement> allServices = getAllServices();
        Map<String, String> links = new HashMap<>();
        for (SelenideElement service : allServices){
            String serviceTitle = service.find(byClassName("g-tile-title")).getAttribute("title");
            String serviceLink = service.getAttribute("href");
            links.put(serviceLink, serviceTitle);
        }
        return links;
    }

    private List<SelenideElement> getAllServices(){
        return $$(byXpath("//*[@data-behavior=\"tileAction\"]/a"));
    }
}
