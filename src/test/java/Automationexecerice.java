

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Automationexecerice {
    RemoteWebDriver driver;
    String hub = "@hub.lambdatest.com/wd/hub";
    String Username="shubhamr";
    String Accesskey="";
    @BeforeMethod
    @Parameters(value={"browser","version"})
    public void setup(String browser, String version, Method m) throws Throwable {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String timeStamp = new SimpleDateFormat("dd_MM_yyyy HH:mm:sss").format(Calendar.getInstance().getTime());
        capabilities.setCapability("build", "build9001");
        capabilities.setCapability("name", m.getName());
        capabilities.setCapability("platform", System.getenv("HYPEREXECUTE_PLATFORM"));
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);

        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        try {
            driver = new RemoteWebDriver(new URL("https://" + Username + ":" + Accesskey + hub), capabilities);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }




    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    @Test
    public void Loginherokuapp() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000);
        //click on form authentication
        driver.findElement(By.xpath("//a[contains(text(),'Form Authentication')]")).click();
        Thread.sleep(1000);
        //ente
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='radius']")).click();
        Thread.sleep(2000);
        String logintext=driver.findElement(By.xpath("//div[@class='example']/h4")).getText();
        System.out.println(logintext);

    }
    @Test
    public void AmazonSearch() {
        try {
            driver.get("https://www.amazon.in/");
            Thread.sleep(4000);
            System.out.println("Navigated to Amazon");
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("iPhone");
            searchBox.submit();

            // Wait for the search results to load
            Thread.sleep(2000);
            // Find the first search result element and click it
            searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.clear();
            searchBox.sendKeys("laptop");
            searchBox.submit();
            Thread.sleep(3000);
            //
            searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.clear();
            searchBox.sendKeys("watches");
            searchBox.submit();

            // Wait for the product page to load
            Thread.sleep(2000);


        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AmazonAddtocart() {
        try {
            driver.get("https://www.amazon.in/");
            Thread.sleep(4000);
            System.out.println("Navigated to Amazon");
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("iPhone");
            searchBox.submit();
            // Wait for the search results to load
            Thread.sleep(2000);
            // Find the first search result element and click it
            driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
            Thread.sleep(2000);

            //switch to new tab of product clicked
            ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
            //switch to active tab
            driver.switchTo().window(wid.get(1));
            System.out.println("Page title of active tab: " + driver.getTitle());
            // Wait for the product page to load
            Thread.sleep(2000);
            //click on addtocart
            WebElement Addtocart = driver.findElement(By.id("add-to-cart-button"));
            Addtocart.click();


        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


}



