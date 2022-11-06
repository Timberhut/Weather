import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class timberhutTest {

    /*
    TC_1_1  - Тест кейс:
    1. Открыть страницу https://openweathermap.org/
    2. Набрать в строке поиска город Paris
    3. Нажать пункт меню Search
    4. Из выпадающего списка выбрать Paris, FR
    5. Подтвердить, что заголовок изменился на "Paris, FR"
     */

    @Test
    public void testH2Text_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver(); // драйвер сосздан

        // исходные данные для теста
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);

        Thread.sleep(1000);

        WebElement seachCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
        );

        seachCityField.click();
        seachCityField.sendKeys(cityName);
        Thread.sleep(1000);

        WebElement seachButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );

        seachButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoisInDropMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']")
        );

        parisFRChoisInDropMenu.click();

        Thread.sleep(2000);

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(3000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit(); // выйти из браузера

    }
}
