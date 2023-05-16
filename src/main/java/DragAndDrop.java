import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DragAndDrop {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        // action.dragAndDrop(draggable, droppable);
        sleep(1000);
        action.moveToElement(draggable).clickAndHold().moveToElement(droppable).release().build().perform();
        sleep(1000);
//        WebElement dropped = driver.findElement(By.xpath("//div[@id='droppable']//p"));
//        assertTrue(dropped.getText().contains("Dropped!"));
//        можно двумя способами проверить, как выше и как ниже.
        assertEquals("Dropped!", droppable.getText());
    }
}
