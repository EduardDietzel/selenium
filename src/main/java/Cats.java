import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Cats {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        // задаем путь файла на жестком диске
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://suninjuly.github.io/cats.html");
    }

    @Test
    public void checkHeaderText(){
        WebElement header = driver.findElement(By.tagName("h1"));
        System.out.println(header.getText());
        assertEquals("Cat memes", header.getText());
    }

    @Test
    public void timeOfLastCatText() {
        // WebElement timeOfLastCatText = driver.findElement(By.xpath("(//small[@class='text-muted'])[6]"));
        WebElement timeOfLastCatText = driver.findElement(By.xpath("//div[@class=\"col-sm-4\"][6]//small"));
        assertEquals("9 mins", timeOfLastCatText.getText());
//        sleep(10000);
    }

    @Test
    public void checkCardsQuantity() {
        List<WebElement> cards = driver.findElements(By.xpath("(//div[@class='col-sm-4'])"));
        System.out.println(cards.size());
        assertEquals(6, cards.size());
    }

    @Test
    public void editButtonSecondCatIsDisplayed() {
        //WebElement editButtonSecondCatIsDisplayed = driver.findElement(By.cssSelector("[class=\"col-sm-4\"]:nth-child(2) .card-body button:nth-child(2)"));
        WebElement editButtonSecondCatIsDisplayed = driver.findElement(By.xpath("(//div[@class='col-sm-4'][2]//button)[2]"));
        assertTrue(editButtonSecondCatIsDisplayed.isDisplayed());
    }

    @Test
    public void allCardsAreDisplayed(){
        List<WebElement> cards = driver.findElements(By.className("col-sm-4"));
//        for (int i = 0; i < cards.size(); i++) {
//            assertTrue(cards.get(i).isDisplayed());
//        }
        for (WebElement card : cards) {
            assertTrue(card.isDisplayed());
        } // для каждого элемента card типа WebElement коллекции cards делаем то, что в скобках {}
//        cards.forEach(card->assertTrue(card.isDisplayed()));
        // Все три варианта работают одинаково, просто реализованы по разному.
    }

    @After
    public void TearDown(){
        driver.quit();
    }
}
