import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class SwitchToTab {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // ВСЕГДА НАДО МЕНЯТЬ ССЫЛКУ НА АКТУАЛЬНУЮ!!!
        driver.get("http://suninjuly.github.io/redirect_accept.html");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    public double funcCalc(double x){
        return log(abs(12*sin(x)));
    }

    @Test
    public void TabTest() throws InterruptedException {
        WebElement redirectButton = driver.findElement(By.cssSelector("button[type='submit']"));
        redirectButton.click();
        sleep(5000);

        //        System.out.println(driver.getWindowHandles());
        //        System.out.println(driver.getWindowHandle());
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        // надо получить список открытых ссылок и выбрать вторую, неактивную:
        driver.switchTo().window(tabs.get(1));
        WebElement x = driver.findElement(By.id("input_value"));
        assertTrue(x.isDisplayed());

        double result = 0;
        System.out.println(x.getText());
        System.out.println(parseDouble(x.getText()));
        result = funcCalc(parseDouble(x.getText()));
        System.out.println("Result: " + result);
        WebElement answerInputField = driver.findElement(By.id("answer"));
        // если искать по css, то писать надо так: #answer
        answerInputField.sendKeys(String.valueOf(result));

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        //sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().contains("Congrats, you've passed the task!"));
        sleep(5000);

    }
}
