package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static WebDriver driver;
    private static final String donutSite = "https://classifieds.ksl.com/search/Recreational-Vehicles/Travel-Trailers-5th-Wheel/priceFrom/4000/priceTo/10000/Private/Sale/Has-Photos";

    @BeforeClass
    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void recent(){
        driver.get(donutSite);
        List<WebElement> trailers = driver.findElements(By.xpath("//section[@class = 'listing-item normal']"));
        String mostRecent = "";
        for(int i = 0; i < trailers.size(); i++)
        {
            WebElement trailer = trailers.get(i);
            WebElement title = trailer.findElement(By.xpath(".//div[2]/h2/div/a"));
            String t = title.getText();
            WebElement price = trailer.findElement(By.xpath(".//div[2]/div[1]"));
            String p = price.getText();
            String l = title.getAttribute("href");
            WebElement time = trailer.findElement(By.className("timeOnSite"));
            String ti = time.getText();

            if(ti.substring(ti.length()-4,ti.length()).equalsIgnoreCase("Mins"))
            {
                mostRecent = "Title: " + t + "      Price: " + p +"\nLink: " + l + "\nPosted since: " + ti;
                System.out.println(mostRecent);
                System.out.println();
            }
        }
    }

    @Test
    public void donutTest(){
        driver.get(donutSite);
        List<WebElement> trailers = driver.findElements(By.xpath("//section[@class = 'listing-item normal']"));
        for(int i = 0; i < trailers.size(); i++)
        {
            WebElement trailer = trailers.get(i);
            WebElement title = trailer.findElement(By.xpath(".//div[2]/h2/div/a"));
            String t = title.getText();
            WebElement price = trailer.findElement(By.xpath(".//div[2]/div[1]"));
            String p = price.getText();
            String l = title.getAttribute("href");
            WebElement time = trailer.findElement(By.className("timeOnSite"));
            String ti = time.getText();

            System.out.println("Title: " + t + "      Price: " + p +"\nLink: " + l + "\nPosted since: " + ti);
            System.out.println();
        }
    }

    @AfterClass
    public static void cleanUp() throws Exception {
        Thread.sleep(2000);
        driver.close();
    }
}
