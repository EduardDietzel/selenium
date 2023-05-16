import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class SwitchToTab2 {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // ВСЕГДА НАДО МЕНЯТЬ ССЫЛКУ НА АКТУАЛЬНУЮ!!!
        driver.get("https://demoqa.com/browser-windows");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void tabTest() throws InterruptedException {
        // click on New Tab button
        // switch to 2nd tab
        // assert contains text "This is a sample page"

        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        // надо получить список открытых ссылок и выбрать вторую, неактивную:
        driver.switchTo().window(tabs.get(1));
        sleep(2000);
        WebElement textOnPage = driver.findElement(By.id("sampleHeading"));
        assertEquals("This is a sample page", textOnPage.getText());
        sleep(2000);
    }
}
