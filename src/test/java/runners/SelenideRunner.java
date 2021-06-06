package runners;

import com.codeborne.selenide.Selenide;
import config.SelenoidConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SelenideRunner {
    public static final String fileName = "services.txt";

    @BeforeClass
    public void setUp() {
        SelenoidConfig config = new SelenoidConfig();
        config.createWebDriverInstance();
    }

    @AfterClass
    public void closeConnection() {
        Selenide.closeWebDriver();
    }
}
