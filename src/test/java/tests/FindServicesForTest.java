package tests;

import config.UrlConfig;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import steps.Steps;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.open;

public class FindServicesForTest extends BasePage {

    @BeforeTest
    public void setUpFile() {
        try {
            Path path = Path.of(fileName);
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllServices() {
        open(UrlConfig.urlPortal);
        Steps.checkAuthorization(mainPage.getUsername());
        mainPage.goToCatalog();
        catalogPage.loadMore();
        List<String> categories = catalogPage.getLinksCategories();
        Steps.checkCategoriesCount(categories.size());
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))){
            for(String category : categories) {
                open(category);
                catalogPage.loadMore();
                Map<String, String> services = catalogPage.getLinksServices();
                    for(Map.Entry<String, String> service : services.entrySet()) {
                        String value = Optional.ofNullable(service.getKey()).orElse("Наименование не найдено");
                        bufferedWriter.write(service.getKey());
                        bufferedWriter.write("\t");
                        bufferedWriter.write(value);
                        bufferedWriter.newLine();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
