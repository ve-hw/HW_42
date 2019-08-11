package facebook;

import java.util.Properties;
import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Safari {

	static String url = "https://facebook.com/login";
	static String email = System.getenv("fb_email");
	static String password = System.getenv("fb_pass");
    static Properties p = new Properties();

	public static void main(String[] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);

		WebDriver driver;
		driver = new SafariDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);

		driver.manage().window().maximize();

		driver.get(url);
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		String copyright = driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getText();

		System.out.println("Browser: " + browserName);
		System.out.println("Title: " + driver.getTitle());
		System.out.println("Copyright: " + copyright);

		// driver.findElement(By.id("email")).sendKeys("t4h0e@yahoo.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email);

		// driver.findElement(By.id("pass")).sendKeys("***");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);

		// driver.findElement(By.id("loginbutton")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton"))).click();

		// driver.findElement(By.xpath("//*[@id=\'u_0_a\']/div[1]/div[1]/div/a")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='u_0_a']/div[1]/div[1]/div/a/span")))
				.click();

		// String friends =
		// driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span[1]")).getText();
		String friends = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a/span[1]"))).getText();

		// driver.findElement(By.id("userNavigationLabel")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel"))).click();

		// WebElement logout =
		// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log Out"))).click();

		System.out.println("You have " + friends + " friends");
		driver.quit();
	}
}