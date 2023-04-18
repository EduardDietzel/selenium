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

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class MathCalk {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://suninjuly.github.io/math.html");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    public double funcCalc(double x){
        return log(abs(12*sin(x)));
    }

    @Test
    public void calculationTest() throws InterruptedException {
        double result = 0;
        WebElement xValue = driver.findElement(By.id("input_value"));
        System.out.println(xValue.getText());
        System.out.println(parseDouble(xValue.getText()));
        result = funcCalc(parseDouble(xValue.getText()));
        System.out.println("Result: " + result);
        WebElement answerInputField = driver.findElement(By.id("answer"));
        // если искать по css, то писать надо так: #answer
        answerInputField.sendKeys(String.valueOf(result));

        // 1. Checkbox I'm the robot
        WebElement robotCheckbox = driver.findElement(By.id("robotCheckbox"));
        robotCheckbox.click();
        // 2. Radiobutton Robots rule
        WebElement radiobuttonRobotsRule = driver.findElement(By.id("robotsRule"));
        radiobuttonRobotsRule.click();
        // 3. Submit button
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        // 4. Alert test check
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().contains("Congrats, you've passed the task!"));
        sleep(5000);
    }

}
