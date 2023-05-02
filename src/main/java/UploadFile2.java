import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class UploadFile2 {

    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demoqa.com/upload-download");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void uploadFileTest() throws InterruptedException {
        // Upload file
        // Check

//        WebElement download = driver.findElement(By.id("downloadButton"));
//        download.click();

        WebElement upload = driver.findElement(By.id("uploadFile"));
        upload.sendKeys("C:/Users/bande/Downloads/textfile.txt");
        WebElement path = driver.findElement(By.id("uploadedFilePath"));
        assertTrue(path.getText().contains("textfile.txt"));
        sleep(5000);
    }
}
