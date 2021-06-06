package pages;

import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;

public class ServicePage {
    private static final SelenideElement serviceName = $(byXpath("//span[@class=\"modal-title-inner-text\"]/span"));
    private static final SelenideElement organizationName = $(byXpath("//a[@class=\"service-organ-link\"]"));
    private static final SelenideElement regulationsLink = $(byXpath("//a[@class=\"reglament-link\"]"));
    private static final List<SelenideElement> electronicServices = $$(byXpath("//h3[@class='b-basic-list-title ' and contains(text(), 'Электронные услуги')]/following-sibling::div/div/a"));
    private static final List<SelenideElement> noElectronicServices = $$(byXpath("//h3[@class='b-basic-list-title ' and contains(text(), 'Не электронные услуги')]/following-sibling::div/div/a"));

    public String getServiceName(){
        return serviceName.getAttribute("innerText");
    }

    public String getOrganizationName(){
        return organizationName.getAttribute("innerText");
    }

    public String getRegulationsLink(){
        return regulationsLink.getAttribute("href");
    }

    public int countElectronicSubServices(){
        return electronicServices.size();
    }

    public int countNoElectronicSubServices(){
        return noElectronicServices.size();
    }

    public List<String> getElectronicServices(){
        List<String> links = new ArrayList<>();
        for (SelenideElement subService : electronicServices){
            String dataTargetId = subService.getAttribute("data-targetid");
            String dataServiceId = subService.getAttribute("data-serviceid");
            String subServiceLink = "https://gosuslugi29.ru/pgu/services/info/targets.htm?id=" +
                    dataTargetId + "&serviceId=" + dataServiceId;
            links.add(subServiceLink);
        }
        return links;
    }

    public List<String> getNoElectronicServices(){
        List<String> links = new ArrayList<>();
        for (SelenideElement subService : noElectronicServices){
            String dataTargetId = subService.getAttribute("data-targetid");
            String dataServiceId = subService.getAttribute("data-serviceid");
            String subServiceLink = "https://gosuslugi29.ru/pgu/services/info/targets.htm?id=" +
                    dataTargetId + "&serviceId=" + dataServiceId;
            links.add(subServiceLink);
        }
        return links;
    }
}
