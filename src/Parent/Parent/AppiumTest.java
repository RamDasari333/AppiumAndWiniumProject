package Parent.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumTest {

	private static String nameOfAVD;

	AppiumDriver driver = null;

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;

	public void startAppiumServer() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		System.out.println(service.isRunning());
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

	}
	public void stopServer() {
		service.stop();
	}
	public void setCapabilities() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("avd", "AndroidTestDevice");
		// capabilities.setCapability("deviceName", "192.168.195.103:5555");
		capabilities.setCapability("driver.class",
				"io.appium.java_client.android.AndroidDriver");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		// capabilities.setCapability("app",
		// "C:\\Users\\nisha.mistry\\Desktop\\flipkart-5-0.apk");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		builder.withCapabilities(capabilities);
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	}

	@BeforeClass
	public void setUp() throws MalformedURLException {
		startAppiumServer();
		setCapabilities();
	}

	@Test
	public void testApp() throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("app launched ");
		Thread.sleep(10000);
		WebElement loginEle = driver
				.findElement(By.xpath("//*[contains(@resource-id,'hintFocusText')]"));
		loginEle.sendKeys("8147740413");

		WebElement btnSignUp =
				driver.findElement(By.id("com.flipkart.android:id/btn_msignup"));
		btnSignUp.click();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String sdkPath = "/Applications/adt-bundle-mac-x86_64-20140702/sdk/";// or
																				// for w
																				// windows
																				// D:/Android/adt-bundle-windows-x86_64-20140702/sdk/
		String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
		String emulatorPath = sdkPath + "tools" + File.separator + "emulator";
		Process p = Runtime.getRuntime().exec("adb -s emulator-5554 shell strace -p 871");
		System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
		String[] aCommand = new String[]{emulatorPath, "-avd", nameOfAVD};
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(180, TimeUnit.SECONDS);
			System.out.println("Emulator launched successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}