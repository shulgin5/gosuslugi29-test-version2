package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class SelenoidConfig {

    public void createWebDriverInstance() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserVersion = "92";
        Configuration.browser = "chrome";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.timeout = 10000;
        Configuration.reportsFolder = "target/screenshots";
        Configuration.clickViaJs = true;
        Configuration.startMaximized = true;
        Configuration.baseUrl = "";
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", false);
    }
}
