
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CreateOrder {
    public static void main(String[] args) throws InterruptedException, IOException {
        String path = "src/MOCK_DATA.csv";
        List<String[]> list = readFromCSV(path);

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Rahim\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");


        driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
        driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
        Thread.sleep(2000);
        driver.findElement(By.name("ctl00$MainContent$login_button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href='Process.aspx']")).click();
        Thread.sleep(2000);

        int randomQuantity = (int)(Math.random() * (100 - 1)) + 1; //generates numbers between 1-100
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(Keys.BACK_SPACE);

        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(randomQuantity+"");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Calculate']")).click();

        int pickUser = (int)(Math.random() * 1001);

        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(list.get(pickUser)[0]);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(list.get(pickUser)[1]);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(list.get(pickUser)[2]);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(list.get(pickUser)[3]);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(list.get(pickUser)[4]);
        Thread.sleep(2000);

        List<WebElement> radioButtons = driver.findElements(By.className("RadioList"));
        int pickCard = (int)(Math.random() * 3);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_"+pickCard)).click();

        Thread.sleep(2000);

        long visa = (long) (Math.random() * (5000000000000000L - 4000000000000000L)) + 4000000000000000L;
        long mastercard = (long) (Math.random() * (6000000000000000L - 5000000000000000L)) + 5000000000000000L;
        long amex = (long) (Math.random() * (400000000000000L - 300000000000000L)) + 300000000000000L;

        if(pickCard == 0){
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(visa+"");
        }
        else if(pickCard==1){
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(mastercard+"");
        }
        else if(pickCard==2){
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(amex+"");
        }

        int month = (int)(Math.random() * (12 - 1)) + 1;
        int year = (int)(Math.random() * (27 - 23)) + 23;
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(String.format("%02d", month)+"/"+year);
        Thread.sleep(2000);

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_logout")).click();
        Thread.sleep(2000);
        driver.close();


    }
    public static List<String[]> readFromCSV(String str) throws IOException{
        List<String[]> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(str));
            String strConv;
            while ((strConv = br.readLine()) != null) {
                String[] eachrow = strConv.split(",");
                result.add(eachrow);
            }
        }
        catch (IOException e){
            System.out.println("Exception");
        }

        return result;
    }
}


