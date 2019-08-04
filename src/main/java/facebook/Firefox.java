package facebook;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.*;

public class Firefox {

	static String url = "https://facebook.com/login";
	static String email = "t4h0e@yahoo.com";
	static String password = "***";

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		Logger.getLogger("").setLevel(Level.OFF);

		String driverPath = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/geckodriver.sh";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/geckodriver.exe";
		else
			throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.gecko.driver", driverPath);
		FirefoxProfile ffprofile = new FirefoxProfile();
		ffprofile.setPreference("dom.webnotifications.enabled", false);
		ffprofile.setPreference("dom.push.enabled", false);

		driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

		driver.get(url);
		// Thread.sleep(1000); // Pause in milliseconds (1000 – 1 sec)
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
		Thread.sleep(3000); // Pause in milliseconds (1000 – 1 sec), adding 3 seconds delay as still working
							// on disabling FF popup notification

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
