import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class Hover {

    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://crossbrowsertesting.github.io/hover-menu.html");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void hoverTest() throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("//li[@class='dropdown']/a"));
        // нам надо сделать hover, но не клик
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdown).perform();
        sleep(5000);
        WebElement secondaryMenu = driver.findElement(By.cssSelector("[class='secondary-dropdown']>a"));
        actions.moveToElement(secondaryMenu).perform();
        WebElement secondaryAction = driver.findElement(By.cssSelector("[onclick='handleSecondaryAction()']"));
        secondaryAction.click();
        sleep(5000);
        // assert Secondary Page
        WebElement secondaryPage = driver.findElement(By.cssSelector("div[class='jumbotron secondary-clicked'] h1"));
        assertTrue(secondaryPage.getText().contains("Secondary Page"));
        // assertEquals();
    }

}
