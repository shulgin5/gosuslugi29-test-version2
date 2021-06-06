package tests;

import org.testng.annotations.*;
import pages.BasePage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Description;
import steps.Steps;
import utils.Utils;

public class MainTest extends BasePage{

    @DataProvider(name = "services", parallel = true)
    public Object[][] allServices() {
        int countLines = Utils.countLinesInFile(fileName);
        System.out.println(countLines);
        Object[][] objects = new Object[countLines][2];
        int i = 0;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))){
            while(bufferedReader.ready()){
                String line = bufferedReader.readLine();
                if (line != null && !line.equals("")) {
                    String[] parameters = line.split("\t");
                    objects[i][0] = parameters[1];
                    objects[i][1] = parameters[0];
                    i++;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Test(dataProvider = "services")
    @Description(value = "Проверка услуги")
    public void serviceTest(String name, String href){
        open(href);
        int countEl = servicePage.countElectronicSubServices();
        int countNoEl = servicePage.countNoElectronicSubServices();
        Steps.checkSubServices(servicePage, countEl, countNoEl);
    }
}
