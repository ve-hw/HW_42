package facebook;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Safari {

	public static void main(String[] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);

		WebDriver driver;
		driver = new SafariDriver();
		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/login/");
		Thread.sleep(2000);
	    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toLowerCase();
	    System.out.println("Browser: " + browserName);
		System.out.println("Title: " + driver.getTitle());
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String copyright = driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getText();
		System.out.println("Copyright: " + copyright);
		driver.findElement(By.id("email")).sendKeys("t4h0e@yahoo.com");
		driver.findElement(By.id("pass")).sendKeys("***");
		driver.findElement(By.id("loginbutton")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\'u_0_a\']/div[1]/div[1]/div/a")).click();
		Thread.sleep(5000);
		String friends = driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span[1]")).getText();
		System.out.println("You have " + friends + " friends");
		Thread.sleep(3000);
		driver.findElement(By.id("userNavigationLabel")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
		logout.click();
		Thread.sleep(3000);

		driver.quit();
	}
}