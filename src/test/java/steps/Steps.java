package steps;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.ServicePage;
import pages.SubServicePage;
import java.util.List;
import java.util.regex.Pattern;
import static com.codeborne.selenide.Selenide.open;

public class Steps {

    @Step(value = "Вход на портал")
    public static void checkAuthorization(String username) {
        Assert.assertEquals(username, "Ростов А.И.");
    }

    @Step(value = "Количество категорий услуг - {count}")
    public static void checkCategoriesCount(int count) {
        Assert.assertTrue(count > 0);
    }

    @Step(value = "Проверка подуслуг: {countEl} эл., {countNoEl} неэл.")
    public static void checkSubServices(ServicePage servicePage, int countEl, int countNoEl) {
        SoftAssert softAssert = new SoftAssert();
        List<String> electronicServices = servicePage.getElectronicServices();
        List<String> noElectronicServices = servicePage.getNoElectronicServices();
        checkElectronicServices(softAssert, electronicServices);
        checkNoElectronicServices(softAssert, noElectronicServices);
        softAssert.assertTrue(countEl > 0, "Электронные услуги отсутствуют!");
        softAssert.assertTrue(countNoEl == 0, "Наличие неэлектронных услуг");
        softAssert.assertAll();
    }

    public static void checkElectronicServices(SoftAssert softAssert, List<String> electronicServices) {
        SubServicePage subServicePage = new SubServicePage();
        for (String link : electronicServices) {
            open(link);
            softAssert.assertTrue(subServicePage.getTemplateLink().length() > 0, "Шаблон заявления не найден");
            softAssert.assertTrue(subServicePage.getExampleLink().length() > 0, "Пример заявления не найден");
            subServicePage.clickButtonAllInfo();
            softAssert.assertTrue(subServicePage.existsButtonGet(), "Нет кнопки 'Получить услугу'");
            //softAssert.assertTrue(subServicePage.existsButtonAppointment(), "Нет кнопки 'Записаться'");
            softAssert.assertTrue(subServicePage.getRegulationsLink().length() > 0, "Не удалось найти ссылку на административный регламент'");
            softAssert.assertTrue(Pattern.matches(".*\\d+.*", subServicePage.getDeadLineComplete()), "Не найден срок оказания услуги");
            softAssert.assertTrue(subServicePage.getCategoriesRecipient().size() > 0, "Отсутствуют данные в блоке 'Категории получателей'");
            softAssert.assertTrue(subServicePage.existsCost(), "Отсутствуют данные в блоке 'Стоимость и порядок оплаты'");
            softAssert.assertTrue(subServicePage.getOrganization().length() > 0, "Не найдено ведомство, предоставляющее услугу");
            softAssert.assertTrue(subServicePage.getRefusals().size() > 0, "Отсутствуют данные в блоке 'Основания для отказа'");
            softAssert.assertTrue(subServicePage.getResults().size() > 0, "Отсутствуют данные в блоке 'Результат оказания услуги'");
        }
    }

    public static void checkNoElectronicServices(SoftAssert softAssert, List<String> electronicServices) {
        SubServicePage subServicePage = new SubServicePage();
        for (String link : electronicServices) {
            open(link);
            softAssert.assertTrue(subServicePage.getTemplateLink().length() > 0, "Шаблон заявления не найден");
            softAssert.assertTrue(subServicePage.getExampleLink().length() > 0, "Пример заявления не найден");
            subServicePage.clickButtonAllInfo();
            softAssert.assertTrue(subServicePage.getRegulationsLink().length() > 0, "Не удалось найти ссылку на административный регламент'");
            softAssert.assertTrue(Pattern.matches(".*\\d+.*", subServicePage.getDeadLineComplete()), "Не найден срок оказания услуги");
            softAssert.assertTrue(subServicePage.getCategoriesRecipient().size() > 0, "Отсутствуют данные в блоке 'Категории получателей'");
            softAssert.assertTrue(subServicePage.existsCost(), "Отсутствуют данные в блоке 'Стоимость и порядок оплаты'");
            softAssert.assertTrue(subServicePage.getOrganization().length() > 0, "Не найдено ведомство, предоставляющее услугу");
            softAssert.assertTrue(subServicePage.getRefusals().size() > 0, "Отсутствуют данные в блоке 'Основания для отказа'");
            softAssert.assertTrue(subServicePage.getResults().size() > 0, "Отсутствуют данные в блоке 'Результат оказания услуги'");
        }
    }
}
