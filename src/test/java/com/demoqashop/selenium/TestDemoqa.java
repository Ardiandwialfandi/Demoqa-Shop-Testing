package com.demoqashop.selenium;

import com.demoqa.selenium.DemocaShop;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestDemoqa {
    WebDriver driver;
    JavascriptExecutor js;
    String locDriver = "C:\\Users\\ARDI\\.cache\\selenium\\chromedriver\\win32\\110.0.5481.77\\chromedriver.exe";

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",locDriver);
        System.out.println("Set Driver Success");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void close(){
        DemocaShop.delayDuration(3);
        driver.quit();
        System.out.println("Thank you!");
        System.out.println("Quit System");
    }

    @Test (priority = 1)
    public void setBrowser(){
        String url = "https://shop.demoqa.com/";
        driver.get(url);
        System.out.println("Get URL : "+url);
        driver.manage().window().maximize();
        System.out.println("maximize windows... Success!");
        String title = driver.getTitle();
        System.out.print("Test Result : "+title);
        Assert.assertTrue(title.contains("ToolsQA Demo Site"));
    }

    @Test (priority = 2)
    public void testMyaccount(){
        DemocaShop.delayDuration(1);
        driver.findElement(By.xpath("/html/body/p/a")).click();
        System.out.println("Dismiss pop up click");
        DemocaShop.delayDuration(1);
        driver.findElement(By.linkText("My Account")).click();
        System.out.println("Get to my account");
        js.executeScript("window.scrollBy(0,500)");
        System.out.println("Scroll bar");
        String login = driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/h2")).getText();
        Assert.assertEquals(login, "LOGIN");
    }

    @Test (priority = 3)
    public void setLogin(){
        DemocaShop.delayDuration(1);
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
        System.out.println("account name : "+nama);
        String expect = "Ardiandwi";
        Assert.assertEquals(nama,expect);
    }

    @Test (priority = 4)
    public void testDasbToCart(){
        DemocaShop.delayDuration(1);
        driver.findElement(By.xpath("//*[@id=\"post-8\"]/div/div/nav/ul/li[1]/a")).click();
        System.out.println("Click dashboard");
        driver.findElement(By.linkText("Checkout")).click();
        System.out.println("Get to checkout");
        String titleCart = driver.getTitle();
        Assert.assertTrue(titleCart.contains("Cart"));
    }

    @Test (priority = 5)
    public void testReturnShop(){
        js.executeScript("window.scrollBy(0,300)");
        System.out.println("Scroll bar");
        DemocaShop.delayDuration(1);
        driver.findElement(By.xpath("(//a[normalize-space()='Return to shop'])[1]")).click();
        System.out.println("Return to shop");
        String titleProducts = driver.getTitle();
        Assert.assertTrue(titleProducts.contains("Products"));
    }

    @Test (priority = 6)
    public void testProductsToCart(){
        js.executeScript("window.scrollBy(0,600)");
        System.out.println("Scroll bar");
        driver.findElement(By.xpath("(//span)[28]")).click();
        DemocaShop.delayDuration(2);
        driver.findElement(By.xpath("(//button[@title='Close (Esc)'])[1]")).click();
        System.out.println("option other product");
        DemocaShop.delayDuration(1);
        js.executeScript("window.scrollBy(0,500)");
        System.out.println("Scroll bar");
        driver.findElement(By.xpath("(//span[contains(@class,'noo-quick-view icon_zoom-in_alt')])[6]")).click();
        DemocaShop.delayDuration(2);
        driver.findElement(By.xpath("(//a[contains(@class,'noo-quick-link')])[1]")).click();

        String titleNameProd = driver.getTitle();
        System.out.println("Product Name : "+titleNameProd);
        Assert.assertTrue(titleNameProd.contains("black strappy"));
    }

    @Test (priority = 7)
    public void testPopup(){
        js.executeScript("window.scrollBy(0,620)");
        System.out.println("Scroll bar");
        DemocaShop.delayDuration(2);
        WebElement color = driver.findElement(By.id("pa_color"));
        Select selectColor= new Select(color);
        selectColor.selectByVisibleText("Black");
        System.out.println("Color Black");

        DemocaShop. delayDuration(2);
        driver.findElement(By.className("qty-increase")).click();
        System.out.println("Quantity Increament");
        driver.findElement(By.className("qty-decrease")).click();
        System.out.println("Quantity Deccreament");
        driver.findElement(By.className("qty-increase")).click();
        System.out.println("Quantity Increament");
        driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();
        System.out.println("Click Add to cart");

        String alertWarn =  driver.switchTo().alert().getText();
        System.out.println("Read Popup : "+alertWarn);
        Assert.assertTrue(alertWarn.contains("Please select"));
    }

    @Test (priority = 8)
    public void testAddtoCart(){
        DemocaShop.delayDuration(1);
        driver.switchTo().alert().accept();

        WebElement size = driver.findElement(By.id("pa_size"));
        Select selectSize= new Select(size);
        int selectedSize = driver.findElements(By.tagName("select")).size();
        Random randomSelect = new Random();
        int selectedRandom = randomSelect.nextInt(selectedSize);
        selectSize.selectByIndex(Math.min(selectedRandom, 1));
        System.out.println("Select size sucess");

        DemocaShop.delayDuration(1);
        driver.findElement(By.className("qty-increase")).click();
        System.out.println("Quantity Increament");
        DemocaShop.delayDuration(1);
        driver.findElement(By.className("qty-decrease")).click();
        System.out.println("Quantity Deccreament");
        DemocaShop.delayDuration(1);
        driver.findElement(By.className("qty-increase")).click();
        System.out.println("Quantity Increament");
        driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();
        System.out.println("Click Add to cart");

        js.executeScript("window.scrollBy(0,100)");
        System.out.println("Scroll bar");

        String note = driver.findElement(By.className("woocommerce-message")).getText();
        System.out.println("Note : "+note);
        Assert.assertTrue(note.contains("have been added to your cart."));

    }

    @Test (priority = 9)
    public void testViewCart(){
        DemocaShop.delayDuration(1);
        driver.findElement(By.xpath("//*[@id=\"noo-site\"]/div[2]/div/div/div[1]/div/a")).click();
        System.out.println("View cart");
        js.executeScript("window.scrollBy(0,600)");
        System.out.println("Scroll bar");

        String productNameView = driver.findElement(By.xpath("//a[normalize-space()='black strappy polka dot ruched midi dress - Black']")).getText();
        Assert.assertTrue(productNameView.contains("BLACK STRAPPY"));
    }

    @Test (priority = 10)
    public void testClearCart(){
        DemocaShop.delayDuration(4);
        driver.findElement(By.xpath("//*[@id=\"post-6\"]/div/div/form/table/tbody/tr[2]/td/a[1]")).click();
        System.out.println("clear cart");
        js.executeScript("window.scrollBy(0,600)");
        System.out.println("Scroll bar");

        String titleCart = driver.getTitle();
        Assert.assertTrue(titleCart.contains("Cart"));
    }

}
