package qa2019;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EndToEndTests {
  public static void main(String[] args){
    String exePath = "C:/Users/zero/Desktop/chromedriver.exe"; // put your path here
    System.setProperty("webdriver.chrome.driver", exePath);
    WebDriver driver = new ChromeDriver();

    driver.get("localhost:3000");

    WebElement element = driver.findElement(By.id("input-box"));

    element.sendKeys("Cheese!");

    //element.submit();
    driver.findElement(By.id("submit-button")).click();


    element.sendKeys("testing the text clear");
    driver.findElement(By.id("result")).clear();

    System.out.println("Page title is: " + driver.getTitle());

    (new WebDriverWait(driver, 10)).until(
        (ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("cheese!"));

    System.out.println("Page title is: " + driver.getTitle());

    driver.quit();
  }
}
