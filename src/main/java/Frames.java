import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class Frames {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // ВСЕГДА НАДО МЕНЯТЬ ССЫЛКУ НА АКТУАЛЬНУЮ!!!
        driver.get("https://demoqa.com/frames");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void frameTest(){
        // в данном случае на странице использованы два разных фрейма
        // поэтому приходится переключаться между ними
        driver.switchTo().frame("frame1");
        WebElement header = driver.findElement(By.id("sampleHeading"));
        assertEquals("This is a sample page", header.getText());
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame2");
        WebElement header2 = driver.findElement(By.id("sampleHeading"));
        assertEquals("This is a sample page", header2.getText());
    }
}
