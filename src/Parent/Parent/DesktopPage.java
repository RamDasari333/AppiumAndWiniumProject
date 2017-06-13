package Parent.Parent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DesktopPage {

	WebDriver driver = null;

	@Test
	public void LauchBrowser() throws InterruptedException {
		setBrowser("IE");
		driver.navigate().to("http://www.google.com");
		Thread.sleep(7000);
		WebElement txtbox = driver.findElement(By.id("lst-ib"));
		System.out.println("is displayed: " + txtbox.isDisplayed());
		txtbox.sendKeys("testng tutorial");
		txtbox.sendKeys(Keys.ENTER);
	}

	public void setBrowser(String browserName) {
		String driverPath;
		if (browserName.equals("Chrome")) {
			driverPath = "C:\\Users\\ramu.dasari\\Basic\\server\\chromedriver.exe";
			File file = new File(driverPath);
			System.out.println("Exists :: " + file.exists());
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver();

		} else if (browserName.equals("Firefox")) {
			driverPath = "C:\\Users\\ramu.dasari\\Basic\\server\\geckodriver.exe";
			File file = new File(driverPath);
			System.out.println("Exists :: " + file.exists());
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
			cap.setCapability(CapabilityType.BROWSER_NAME, "Firefox");
			driver = new FirefoxDriver(cap);

		} else if (browserName.equals("IE")) {
			driverPath = "C:\\Users\\ramu.dasari\\Basic\\server\\IEDriverServer.exe";
			File file = new File(driverPath);
			System.out.println("Exists :: " + file.exists());
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(
					InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);

			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver(dc);

		}
		System.out.println("launching " + browserName + " browser");

	}

	@AfterClass
	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}
}
