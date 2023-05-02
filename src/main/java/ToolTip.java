import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToolTip {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/tool-tips");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void allTooltipsTest() throws InterruptedException {
        WebElement hoverMeToSeeBtn = driver.findElement(By.id("toolTipButton"));
        Actions actions = new Actions(driver);
        // чтобы навести курсор используем метод moveToElement
        actions.moveToElement(hoverMeToSeeBtn).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btnToolTip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className ("tooltip-inner")));
        // WebElement btnToolTip = driver.findElement(By.className("tooltip-inner"));
        assertEquals("You hovered over the Button", btnToolTip.getText());
        sleep(1000);

        WebElement inputField = driver.findElement(By.id("toolTipTextField"));
        //hover inputField
        actions.moveToElement(inputField).perform();
        //assert "You hovered over the text field"
        sleep(1000);
        WebElement inputFiledTooltip  =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));
        assertEquals("You hovered over the text field", inputFiledTooltip.getText());
        sleep(1000);

        WebElement contraryLink = driver.findElement(By.xpath("//a[contains(text(),'Contrary')]"));
        actions.moveToElement(contraryLink).perform();
        //assert "You hovered over the Contrary"
        sleep(5000);
        WebElement contraryLinkTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));
        assertEquals("You hovered over the Contrary", contraryLinkTooltip.getText());
        sleep(5000);

        WebElement numbersLink = driver.findElement(By.xpath("//a[contains(text(),'1.10.32')]"));
        actions.moveToElement(numbersLink).perform();
        sleep(1000);
        WebElement yourNumbers =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));
        assertTrue(yourNumbers.getText().contains("You hovered over the 1.10.32"));
    }

}
