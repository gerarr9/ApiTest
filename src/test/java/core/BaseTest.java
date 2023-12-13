package core;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver chromeDriver;


    @BeforeEach
    public void setUp(){
        // System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        System.setProperty("webdriver.chrome.driver","C:\\WebDrivers\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}
