import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TimberhutGitHubTest {

    /**
     * TC_11_01 OK
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
     * и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void timberhutGitHubTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();
        // исходные данные для теста
        String url = "https://openweathermap.org/";
        String expectedResult = "Guide";

        driver.get(url);

        Thread.sleep(1000);

        WebElement guideMenu = driver.findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[contains(text(),'Guide')]")
        );
        guideMenu.click();

        Thread.sleep(3000);

        WebElement guideHeader = driver.findElement(
                By.xpath("//h1[contains(.,'Guide')]")
        );

        String actualResult = guideHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);


        driver.quit(); // выйти из браузера

    }

    /**
     * TC_11_02 OK
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test
    public void timberhutGitHubTestChangingToFtemperature() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";


        driver.get(url);

        Thread.sleep(1000);

        WebElement guideMenu = driver.findElement(
                By.xpath("//div[@class= 'option'][contains(text(), '°F')]")
        );
        guideMenu.click();

        Thread.sleep(1000);

        WebElement temperatureInF = driver.findElement(
                By.xpath("//span[@class= 'heading'][contains(text(), '°F')]")
        );
        Thread.sleep(1000);

        String expectedResult = String.valueOf(temperatureInF.getText().contains("F")); //a[contains(text(),'часть текста')]

        String actualResult = String.valueOf(temperatureInF.getText().contains("F"));

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    /**
     * TC_11_03 OK
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом
     * “We use cookies which are essential for the site to work. We also use non-essential
     * cookies to help us improve our services. Any data collected is anonymised.
     * You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */
    @Test
    public void timberhutGitHubTestLowerBar() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();
        String expectedResultLowerBarText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data collected is anonymised. " +
                "You can allow all cookies or manage them individually.";
        String expectedResultLeftButtonText = "Allow all";
        String expectedResultRigthButtonText = "Manage cookies";

        // исходные данные для теста
        String url = "https://openweathermap.org/";

        driver.get(url);

        Thread.sleep(1000);

        String actualResultLowerBarMenuText = driver.findElement(
                By.className("stick-footer-panel__description")
        ).getText();

        Assert.assertEquals(actualResultLowerBarMenuText, expectedResultLowerBarText);

        String actualResultLowerBarMenuLeftButton = driver.findElement(
                By.xpath("//button[@class ='stick-footer-panel__link']")
        ).getText();

        Assert.assertEquals(actualResultLowerBarMenuLeftButton, expectedResultLeftButtonText);

        String actualResultLowerBarMenuRigthButton = driver.findElement(
                By.xpath("//a[@class ='stick-footer-panel__link']")
        ).getText();

        Assert.assertEquals(actualResultLowerBarMenuRigthButton, expectedResultRigthButtonText);

        Thread.sleep(1000);

        driver.quit();

    }

    /**
     * TC_11_04 OK
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test
    public void timberhutGitHubTestSupportMenu3SubMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();
        String expectedResultFaq = "FAQ";
        String expectedResultStart = "How to start";
        String expectedResultQuestion = "Ask a question";

        // исходные данные для теста
        String url = "https://openweathermap.org/";

        driver.get(url);

        Thread.sleep(2000);

        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportMenu.click();

        String actualResultSupportDropDownFirstButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/faq']")
        ).getText();

        Assert.assertEquals(actualResultSupportDropDownFirstButton, expectedResultFaq);

        String actualResultSupportDropDownSecondButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/appid']")
        ).getText();

        Assert.assertEquals(actualResultSupportDropDownSecondButton, expectedResultStart);

        String actualResultSupportDropDownThirdButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='https://home.openweathermap.org/questions']")
        ).getText();

        Assert.assertEquals(actualResultSupportDropDownThirdButton, expectedResultQuestion);

        driver.quit();

    }

    /**
     * TC_11_05 OK
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     **/

    @Test
    public void timberhutGitHubTestAskQuestionForm() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";
        String email = "test@gmail.com";
        String expectedResultAnswerMessage = "reCAPTCHA verification failed, please try again.";
        String message = "How to start question";

        driver.get(url);

        Thread.sleep(2000);

        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportMenu.click();

        WebElement supportDropDownMenuAskQuestion = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='https://home.openweathermap.org/questions']")
        );
        Thread.sleep(2000);
        supportDropDownMenuAskQuestion.click();


        /* Store the current window handle
        //String winHandleBefore = driver.getWindowHandle(); - для возврата в прежн закладку
        // Perform the click operation that opens new window
        // Switch to new window opened

         */

        Thread.sleep(2000);

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        /*driver.switchTo().window(winHandleBefore);  - Switch back to original browser (first window)

//        WebElement userCheckBox = driver.findElement(
//                By.xpath("// label[@for='question_form_is_user_true']/input")
//        );
//        userCheckBox.click();

         */

        WebElement emailTextArea = driver.findElement(
                By.xpath("//div[@class = 'col-sm-8']//input[@id='question_form_email']")
        );
        Thread.sleep(2000);

        emailTextArea.click(); emailTextArea.sendKeys(email);

        WebElement subjectTextArea = driver.findElement(
                By.xpath("//select[@class= 'form-control select required']")
        );
        subjectTextArea.click();

        WebElement subjectTextMenuTech = driver.findElement(
                By.xpath("//select[@class= 'form-control select required']//option[@value='Tech Issue']")
        );
        subjectTextMenuTech.click();

        WebElement textMenuArea = driver.findElement(
                By.xpath("//div[@class= 'col-sm-8']//textarea")
        );
        textMenuArea.click(); textMenuArea.sendKeys(message);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@class='btn btn-default']")
        );
        submitButton.click();

        String actualResulrmessageCapcha = driver.findElement(
                By.xpath("//div[@class='help-block']")
        ).getText();

        Thread.sleep(2000);

        Assert.assertEquals(actualResulrmessageCapcha,expectedResultAnswerMessage);

        driver.quit();
    }

    /**
     * TC_11_06 CAPCHA?
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */

    @Test
    public void timberhutGitHubTestAskQuestionNoEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";
        String message = "How to start question";

        driver.get(url);

        Thread.sleep(2000);

        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportMenu.click();

        WebElement supportDropDownMenuAskQuestion = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='https://home.openweathermap.org/questions']")
        );
        Thread.sleep(2000);
        supportDropDownMenuAskQuestion.click();

        Thread.sleep(2000);

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        /*
        // Store the current window handle
        //String winHandleBefore = driver.getWindowHandle(); - для возврата в прежн закладку
        // Perform the click operation that opens new window
        // Switch to new window opened
        //driver.switchTo().window(winHandleBefore);  - Switch back to original browser (first window)

         */

        Thread.sleep(2000);

        WebElement subjectTextArea = driver.findElement(
                By.xpath("//select[@class= 'form-control select required']")
        );
        subjectTextArea.click();

        WebElement subjectTextMenuTech = driver.findElement(
                By.xpath("//select[@class= 'form-control select required']//option[@value='Tech Issue']")
        );
        subjectTextMenuTech.click();

        WebElement textMenuArea = driver.findElement(
                By.xpath("//div[@class= 'col-sm-8']//textarea")
        );
        textMenuArea.click(); textMenuArea.sendKeys(message);

        WebElement capcha = driver.findElement(
                By.xpath("//iframe[@title='reCAPTCHA']")
        );
        capcha.click();


        WebElement submitButton = driver.findElement(
                By.xpath("//input[@class='btn btn-default']")
        );
        submitButton.click();

        Thread.sleep(2000);


        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();

        Thread.sleep(10000);

        driver.quit();
    }

    /**
     * TC_11_07 OK
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     *
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */

    @Test
    public void timberhutGitHubTestChangingTemperatureValues() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";
        String temperatureValue = "°C";

        driver.get(url);

        Thread.sleep(2000);

        WebElement ChangingTemperatureToF = driver.findElement(
                By.xpath("//div[@class = 'option'][contains(text(),'°F')]")
        );
        ChangingTemperatureToF.click();

        Thread.sleep(2000);

        WebElement ChangingTemperatureToC = driver.findElement(
                By.xpath("//div[@class = 'option'][contains(text(),'°C')]")
        );
        ChangingTemperatureToC.click();

        Thread.sleep(2000);

        String temperatureC = driver.findElement(
                By.xpath("//span[@class = 'heading'][contains(text(),'°C')]")
        ).getText();

        Boolean actualResult = temperatureC.contains(temperatureValue);


        Assert.assertTrue(actualResult);

        driver.quit();
    }

    /**
     * TC_11_08 OK
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     *
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    @Test
    public void timberhutGitHubTestCurrentUrl() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";

        driver.get(url);

        Thread.sleep(2000);

        WebElement imageBanner = driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        imageBanner.click();


        Thread.sleep(2000);

        String expectedResult = "https://openweathermap.org/";
        String actualResult = driver.getCurrentUrl();


        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /**
     * TC_11_09 OK
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */

    @Test
    public void timberhutGitHubTestRomeCheckWeather() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";
        String expectedResultCity = "Rome";
        String searchValue1 = "find";
        String searchValue2 = "Rome";

        driver.get(url);

        Thread.sleep(2000);

        WebElement navigationBarSearch = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@type='text']")
        );
        navigationBarSearch.click();
        navigationBarSearch.sendKeys(expectedResultCity);
        navigationBarSearch.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        String strUrl = driver.getCurrentUrl();
        Boolean actualResult = strUrl.contains(searchValue1) && strUrl.contains(searchValue2);


        Assert.assertTrue(actualResult);

       String actualResultSearchBar = driver.findElement(
                By.xpath("//input[@class]")
        ).getAttribute("value");

       Assert.assertEquals(actualResultSearchBar,expectedResultCity);

       driver.quit();

    }

    /**
     * TC_11_10 OK
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */

    @Test
    public void timberhutGitHubTestThirtyButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromDrivers/chromedriver chrom107selenium");
        WebDriver driver = new ChromeDriver();

        // исходные данные для теста
        String url = "https://openweathermap.org/";
        int expectedResult = 30; // ожидаем 30 элементов (30 оранжевых кнопок)

        driver.get(url);

        Thread.sleep(2000);

        WebElement apiMenu = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li//a[@href='/api']")
        );
        apiMenu.click();

       int actualResultOrangeButton = driver.findElements(
                By.xpath("//a[contains(@class,'orange')]")
       ).size();

       // или такой xpath - //a[contains(@class, 'btn_block orange round') or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(actualResultOrangeButton,expectedResult);

        driver.quit();
    }

}
