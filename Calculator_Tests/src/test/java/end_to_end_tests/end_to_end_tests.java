package end_to_end_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class end_to_end_tests {

    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.calculator.net/carbohydrate-calculator.html");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void setMetricFormData(String age, boolean man, String height, String weight){
        WebElement cage = driver.findElement(By.xpath("//*[@id=\"cage\"]"));
        WebElement cheight = driver.findElement(By.xpath("//*[@id=\"cheightmeter\"]"));
        WebElement cweight = driver.findElement(By.xpath("//*[@id=\"ckg\"]"));
        WebElement MSJ = driver.findElement(By.xpath("//*[@id=\"ccsettingcontent\"]/div[2]/div[1]/label"));
        WebElement activeDropDown = driver.findElement(By.xpath("//*[@id=\"cactivity\"]"));
        Select select  = new Select(activeDropDown);
        select.selectByIndex(2);

        cage.clear();
        cage.sendKeys(age);
        if(man){
            driver.findElement(By.xpath("//*[@id=\"calinputtable\"]/tbody/tr[2]/td[2]/label[1]")).click();
        }else{
            driver.findElement(By.xpath("//*[@id=\"calinputtable\"]/tbody/tr[2]/td[2]/label[2]")).click();
        }

        cheight.clear();
        cheight.sendKeys(height);
        cweight.clear();
        cweight.sendKeys(weight);

    }



    @Test
    public void runMetericCalculationValid() throws InterruptedException{

        driver.get("https://www.calculator.net/carbohydrate-calculator.html");

        setMetricFormData("24",false,"175","155");


        driver.findElement(By.cssSelector("input[type='submit'][value='Calculate']")).click();

        Thread.sleep(2000);

        List<WebElement> result = driver.findElements(By.className("h2result"));
        System.out.println(result.size());
        Assert.assertEquals(result.size(),1);



    }


    @Test
    public void runMetricCalculationInvalid() throws InterruptedException{

        driver.get("https://www.calculator.net/carbohydrate-calculator.html");

        setMetricFormData("12",false,"175","0");

        driver.findElement(By.cssSelector("input[type='submit'][value='Calculate']")).click();

        Thread.sleep(2000);


        List<WebElement> result = driver.findElements(By.className("h2result"));


        Assert.assertEquals(result.size(),0);



    }

    @Test
    public void runMetricCalculationBlank() throws InterruptedException{

        driver.get("https://www.calculator.net/carbohydrate-calculator.html");

        setMetricFormData("",false,"","");

        driver.findElement(By.cssSelector("input[type='submit'][value='Calculate']")).click();

        Thread.sleep(2000);


        List<WebElement> result = driver.findElements(By.className("h2result"));

        Assert.assertEquals(result.size(),0);


    }



}
