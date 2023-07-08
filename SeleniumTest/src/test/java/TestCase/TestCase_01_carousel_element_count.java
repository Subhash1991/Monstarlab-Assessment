package TestCase;
import PageObjects.Homepage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestCase_01_carousel_element_count {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://www.mall.cz/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath(Homepage.acceptCookiesButton())).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // to scroll to bottom to load all carousels
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // to wait until last carousel is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.lastCarouselElement())));


    }

    @AfterTest
    public void quitBrowser() {
        driver.quit();
    }

    // to loop the test based on number of carousel count
    @DataProvider(name = "carouselElements")
    public Object[][] getCarouselElementCount() {
        // to
       int count = driver.findElements(By.xpath(Homepage.carouselList())).size();
        Object[][] data = new Object[count][1];
        for (int i = 0; i < count; i++) {
            data[i][0] = i ;
        }
        return data;
    }

    @Test(dataProvider = "carouselElements")
    public void carousel(int index) {

        // to store each carousels one by one in list
        List<WebElement> carousels = driver.findElements(By.xpath(Homepage.tabelList()));
        // selecting each carousels based on index
        WebElement carousel = carousels.get(index);
        // to get number of items available in each carousels
        List<WebElement> carouselItemElements = carousel.findElements(By.cssSelector("li"));
        int elementCount = carouselItemElements.size()/6;
        System.out.println("number of elements "+ elementCount);
        Assert.assertEquals(elementCount,15);

    }

}
