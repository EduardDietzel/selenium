import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Registration{
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://suninjuly.github.io/registration1.html");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

//    @Test
//    public void placeholdersTest() {
//        WebElement firstNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your first name']"));
//        assertEquals("Input your first name", firstNameInputField.getAttribute("placeholder"));
//        WebElement lastNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your last name']"));
//        assertEquals("Input your last name", lastNameInputField.getAttribute("placeholder"));
//    }

    @Test
    public void successfulRegAllFields() throws InterruptedException {
        WebElement firstNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your first name']"));
        assertEquals("Input your first name", firstNameInputField.getAttribute("placeholder"));
        firstNameInputField.sendKeys("John");

        WebElement lastNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your last name']"));
        assertEquals("Input your last name", lastNameInputField.getAttribute("placeholder"));
        lastNameInputField.sendKeys("Smit");
        // email, phone, address - добавить так же.
        WebElement emailInputField = driver.findElement(By.cssSelector("input[placeholder='Input your email']"));
        assertEquals("Input your email", emailInputField.getAttribute("placeholder"));
        emailInputField.sendKeys("John@gmail.com");
        WebElement phoneInputField = driver.findElement(By.cssSelector("input[placeholder='Input your phone:']"));
        assertEquals("Input your phone:", phoneInputField.getAttribute("placeholder"));
        phoneInputField.sendKeys("765654545676");
        WebElement addressInputField = driver.findElement(By.cssSelector("input[placeholder='Input your address:']"));
        assertEquals("Input your address:", addressInputField.getAttribute("placeholder"));
        addressInputField.sendKeys("98779, Berlin, Turmstrasse 65");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        sleep(2000);
        submitButton.click();
        // sleep(5000);
        WebElement headerSuccess = driver.findElement(By.tagName("h1"));
        assertEquals("Congratulations! You have successfully registered!", headerSuccess.getText());
        assertTrue(headerSuccess.getText().contains("Congratulations!"));
        System.out.println(driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains("registration_result"));
    }

    @Test
    public void successfulRegRequaredFields() throws InterruptedException {
        WebElement firstNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your first name']"));
        firstNameInputField.sendKeys("John");
        WebElement lastNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your last name']"));
        lastNameInputField.sendKeys("Smit");
        WebElement emailInputField = driver.findElement(By.cssSelector("input[placeholder='Input your email']"));
        emailInputField.sendKeys("John@gmail.com");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        sleep(2000);
        submitButton.click();
        sleep(5000);
    }

    @Test
    public void withoutFirstName() throws InterruptedException {
        WebElement lastNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your last name']"));
        lastNameInputField.sendKeys("Smit");
        // email, phone, address - добавить так же.
        WebElement emailInputField = driver.findElement(By.cssSelector("input[placeholder='Input your email']"));
        emailInputField.sendKeys("John@gmail.com");
        WebElement phoneInputField = driver.findElement(By.cssSelector("input[placeholder='Input your phone:']"));
        phoneInputField.sendKeys("765654545676");
        WebElement addressInputField = driver.findElement(By.cssSelector("input[placeholder='Input your address:']"));
        addressInputField.sendKeys("98779, Berlin, Turmstrasse 65");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        //sleep(2000);
        submitButton.click();
        WebElement firstNameInputField = driver.findElement(By.cssSelector("input[placeholder='Input your first name']"));
        assertEquals("Please fill out this field.", firstNameInputField.getAttribute("validationMessage"));
        
    }

}
