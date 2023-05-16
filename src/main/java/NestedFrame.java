import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NestedFrame {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // ВСЕГДА НАДО МЕНЯТЬ ССЫЛКУ НА АКТУАЛЬНУЮ!!!
        driver.get("https://demoqa.com/nestedframes");
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
        WebElement body = driver.findElement(By.cssSelector("body"));
        assertEquals("Parent frame", body.getText());
//        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
//        driver.switchTo().frame(iframes.get(0));
        // можно и вот так:
        WebElement childFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(childFrame);
        // или вот так:
        //driver.switchTo().frame(0);

        WebElement body2 = driver.findElement(By.tagName("p"));
        assertEquals("Child Iframe", body2.getText());
    }

}
