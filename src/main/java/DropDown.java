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

import static java.lang.Integer.parseInt;
import static java.lang.Math.*;
import static org.junit.Assert.assertTrue;

public class DropDown {

    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://suninjuly.github.io/selects1.html");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void dropDownTest(){
        // находим по локаторам (по id в данном случае) наши элементы
        // по css: [id="num1"] , по xpath //*[@id="num1"]
        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        // нам надо сложить два элемента, но не как строки, а как числа
        // num1.getText();
        int sum = parseInt(num1.getText()) + parseInt(num2.getText());
        WebElement dropDown = driver.findElement(By.xpath("//*[@id='dropdown']"));
        dropDown.click();
        WebElement answerOption = driver.findElement(By.cssSelector("[value='" + sum + "']"));
        answerOption.click();
        // click on Submit button
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        // assert alert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().contains("Congrats, you've passed the task!"));
    }
}
