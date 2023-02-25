package com.demoqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DemocaShop {
    public static void main(String[] args) {
        String locDriver = "C:\\Users\\ARDI\\.cache\\selenium\\chromedriver\\win32\\110.0.5481.77\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",locDriver);
        System.out.println("Set Driver Success");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String url = "https://shop.demoqa.com/";
        driver.get(url);
        System.out.println("Get URL : "+url);
        driver.manage().window().maximize();
        System.out.println("maximize windows... Success!");

        String title = driver.getTitle();
        System.out.print("Test Result : ");
        if(driver.getTitle().equals(title)){
            System.out.println("Pass!");
        }else{
            System.out.println("Failed!");
        }

        driver.findElement(By.xpath("/html/body/p/a")).click();
        System.out.println("Dismiss pop up click");
        delayDuration(1);
        driver.findElement(By.linkText("My Account")).click();
        System.out.println("Get to my account");

        js.executeScript("window.scrollBy(0,500)");
        System.out.println("Scroll bar");

        driver.findElement(By.name("username")).sendKeys("Ardiandwi");
        System.out.println("Set Username");
        driver.findElement(By.name("password")).sendKeys("Ard14&','//");
        System.out.println("Set Password");
        driver.findElement(By.id("rememberme")).click();
        System.out.println("Remember Me click");
        driver.findElement(By.name("login")).click();
        System.out.println("Click Login");

        js.executeScript("window.scrollBy(0,300)");
        System.out.println("Scroll bar");

        String nama = driver.findElement(By.xpath("//*[@id=\"post-8\"]/div/div/div/p[1]/strong[1]")).getText();
        System.out.print("account name : ");
        if(nama.equals("Ardiandwi")){
            System.out.println("Pass!");
        }else{
            System.out.println("Failed!");
        }

        delayDuration(1);
        driver.findElement(By.xpath("//*[@id=\"post-8\"]/div/div/nav/ul/li[1]/a")).click();
        System.out.println("Click dashboard");
        driver.findElement(By.linkText("Checkout")).click();
        System.out.println("Get to checkout");
        js.executeScript("window.scrollBy(0,300)");
        System.out.println("Scroll bar");
        delayDuration(1);
        driver.findElement(By.xpath("(//a[normalize-space()='Return to shop'])[1]")).click();
        System.out.println("Return to shop");
        driver.findElement(By.xpath("(//div[contains(@class,'noo-product-inner')])[2]")).click();
        System.out.println("Click product to cart");


        js.executeScript("window.scrollBy(0,600)");
        System.out.println("Scroll bar");
        delayDuration(3);

        WebElement color = driver.findElement(By.id("pa_color"));
        Select selectColor= new Select(color);
        selectColor.selectByVisibleText("Black");
        System.out.println("Color Black");

        WebElement size = driver.findElement(By.id("pa_size"));
        Select selectSize= new Select(size);
        int selectedSize = driver.findElements(By.tagName("select")).size();
        Random randomSelect = new Random();
        int selectedRandom = randomSelect.nextInt(selectedSize);
        selectSize.selectByIndex(selectedRandom);
        System.out.println("Select size sucess");

        delayDuration(2);
        driver.findElement(By.className("qty-increase")).click();
        System.out.println("Quantity Increament");
        driver.findElement(By.className("qty-decrease")).click();
        System.out.println("Quantity Deccreament");
        driver.findElement(By.className("qty-increase")).click();
        System.out.println("Quantity Increament");
        driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();
        System.out.println("Click Add to cart");

        js.executeScript("window.scrollBy(0,500)");
        System.out.println("Scroll bar");

        String note = driver.findElement(By.className("woocommerce-message")).getText();
        System.out.println("Note : "+note);
        driver.findElement(By.xpath("//*[@id=\"noo-site\"]/div[2]/div/div/div[1]/div/a")).click();
        System.out.println("View cart");

        js.executeScript("window.scrollBy(0,600)");
        System.out.println("Scroll bar");
        driver.findElement(By.xpath("//*[@id=\"post-6\"]/div/div/form/table/tbody/tr[2]/td/a[1]")).click();
        System.out.println("clear cart");

        js.executeScript("window.scrollBy(0,600)");
        System.out.println("Scroll bar");

        delayDuration(3);
        driver.quit();
        System.out.println("Quit System");

    }

    public static void delayDuration(long time){
        try{
            Thread.sleep(time*1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}