package org.example;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Trailer {
    private static WebDriver driver;
    private static final String donutSite = "https://classifieds.ksl.com/search/Recreational-Vehicles/Travel-Trailers-5th-Wheel/priceFrom/4000/priceTo/10000/Private/Sale/Has-Photos";

    public Trailer() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    public String recent(){
        driver.get(donutSite);
        List<WebElement> trailers = driver.findElements(By.xpath("//section[@class = 'listing-item normal']"));
        String mostRecent = "";
        for(int i = 0; i < 3; i++)
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
                String temp = ti.substring(0,2).replace(" ","");
                int timer = Integer.parseInt(temp);
                if(timer < 20)
                {
                    if(mostRecent.equals(""))
                    {
                        mostRecent = "Title: " + t + "      Price: " + p +"\nLink: " + l + "\nPosted since: " + ti;
                    }
                    else {
                        mostRecent = mostRecent + "\n\n" + "Title: " + t + "      Price: " + p +"\nLink: " + l + "\nPosted since: " + ti;
                    }
                    System.out.println(mostRecent);
                    System.out.println();
                }
            }
        }
        return mostRecent;
    }

    public void closeDriver()
    {
        driver.close();
    }
}
