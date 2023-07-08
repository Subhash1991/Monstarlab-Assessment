package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {

    public static String acceptCookiesButton(){
        return "//button[@class='rounded-button rounded-button--tertiary']";
    }
    public static String lastCarouselElement(){
        return "//article[@data-scroll-id='20528762']" ;

    }
    public static String carouselList(){
        return  "//div[@data-testid='cms-carousel-wrapper']";
    }
    public static String  tabelList(){
        return "//ul[@class='hooper-track']";

    }

}
