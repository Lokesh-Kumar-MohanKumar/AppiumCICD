package com.AppiumJava.Practice;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class FacebookWebAssignment2 extends Base {

	@BeforeTest
	public void setUp() throws IOException {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("FacebookWebMobileTestReport.html");
		spark.config().setDocumentTitle("Mobile Automation Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Facebook Mobile Automation Report");
		extent.attachReporter(spark);
	}

	@Test
	public static void facebookWebRunner() throws InterruptedException, IOException {

		// Driver Session with Desired Capabilities
		AndroidDriver<AndroidElement> driver = createDriverSessionAndCapabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ExtentTest test = extent
				.createTest("Verify the User can able to do Login and Logout Flow using Facebook Web application")
				.assignAuthor("Lokesh").assignCategory("Web").assignDevice("MobileChrome");
		test.info("Test Started");

		// Objects
		By button_acceptAndContinue = By.id("com.android.chrome:id/terms_accept");
		By button_next = By.id("com.android.chrome:id/next_button");
		By button_noThanks = By.id("com.android.chrome:id/negative_button");
		By link_closeLanguage = MobileBy.AccessibilityId("Close");
		By textbox_userName = By.xpath("(//android.widget.EditText)[1]");
		By textbox_password = By.xpath("//android.widget.EditText[@text='Password']");
		By button_login = By.xpath("//android.widget.Button[@text='Log In']");
		By link_notNowLoginInfo = By.xpath("//android.widget.Button[@text='Not Now']");
		By link_blockNotification = MobileBy.xpath("//android.widget.Button[@text='Block']");
		By icon_homePage = By.xpath("//android.widget.Image[@text='Lokesh Kumar, profile picture']");
		By link_mainMenu = By.xpath("//android.widget.Button[@text='Main Menu']");
		By link_menu = By
				.xpath("//android.widget.Button[@text='Change the information Lokesh Kumar has saved on this device']");
		By link_removeAccount = By.xpath("//android.widget.Button[@text='Remove account from device']");
		By link_MyAccount = By.xpath("//android.widget.TextView[@text='My Account']");

		// Open Chrome Browser and Enter url
		openChromeGetLoginPage(driver, test, button_acceptAndContinue, button_next, button_noThanks);

		// Login Flow
		login(driver, test, link_closeLanguage, textbox_userName, textbox_password, button_login, link_notNowLoginInfo,
				link_blockNotification, icon_homePage);

		// Swipe Feed
		TouchAction t = swipeFeed(driver, test);

		// Logout Flow
		logout(driver, test, link_mainMenu, t);

		// Remove My Account Flow
		removeMyAccount(driver, test, link_menu, link_removeAccount, link_MyAccount);
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
	}

	private static void openChromeGetLoginPage(AndroidDriver<AndroidElement> driver, ExtentTest test,
			By button_acceptAndContinue, By button_next, By button_noThanks) throws InterruptedException {
		test.log(Status.INFO, "Started Test Execution");
		driver.findElement(button_acceptAndContinue).click();
		driver.findElement(button_next).click();
		driver.findElement(button_noThanks).click();
		Thread.sleep(2000);
		test.log(Status.INFO, "Launching URL");
		String url = "https://www.facebook.com/";
		driver.get(url);
		Thread.sleep(2000);
		test.pass("Facebook Page Opened",
				MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.INFO, "Opened Facebook Successfully");
	}

	private static void login(AndroidDriver<AndroidElement> driver, ExtentTest test, By link_closeLanguage,
			By textbox_userName, By textbox_password, By button_login, By link_notNowLoginInfo,
			By link_blockNotification, By icon_homePage) throws InterruptedException {
		test.log(Status.INFO, "Login Flow Started");
		try {
			driver.findElement(link_closeLanguage).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		test.log(Status.INFO, "Clicking on Close Language");
		driver.findElement(textbox_userName).sendKeys("lokeshdon95@yahoo.com");
		Thread.sleep(5000);
		test.log(Status.INFO, "Entering Username");
		driver.findElement(textbox_password).sendKeys("lokeshcool11");
		Thread.sleep(2000);
		test.log(Status.INFO, "Entering Password");
		driver.findElement(button_login).click();
		Thread.sleep(2000);
		test.log(Status.INFO, "Login Button clicked successfully");
		try {
			driver.findElement(link_closeLanguage).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		test.log(Status.INFO, "Clicking on Close Language");
		driver.findElement(link_notNowLoginInfo).click();
		test.log(Status.INFO, "Clicking on Not Now Link");

		try {
			driver.findElement(link_closeLanguage).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		driver.findElement(link_blockNotification).click();
		test.log(Status.INFO, "Clicking on Block Notification");

		driver.findElement(icon_homePage).getText().equalsIgnoreCase("Lokesh Kumar, profile picture");
		Thread.sleep(2000);
		test.pass("User Logged In", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.PASS, "Successfully Logged In");
	}

	private static TouchAction swipeFeed(AndroidDriver<AndroidElement> driver, ExtentTest test)
			throws InterruptedException {
		test.log(Status.INFO, "Swipe Feed");
		TouchAction t = new TouchAction(driver);
		t.press(PointOption.point(400, 1950)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(440, 440)).release().perform();
		test.pass("Swipped Up", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		t.press(PointOption.point(440, 440)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(400, 1950)).release().perform();
		test.pass("Swipped Down", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		Thread.sleep(2000);
		test.log(Status.PASS, "Swipping done Successfully");
		return t;
	}

	private static void logout(AndroidDriver<AndroidElement> driver, ExtentTest test, By link_mainMenu, TouchAction t)
			throws InterruptedException {
		test.log(Status.INFO, "Log Out Flow");
		driver.findElement(link_mainMenu).click();
		Thread.sleep(2000);
		test.log(Status.INFO, "Clicking on Main Menu");
		for (int i = 0; i < 4; i++) {
			t.press(PointOption.point(400, 1950)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
					.moveTo(PointOption.point(440, 440)).release().perform();
		}
		t.tap(PointOption.point(257, 2227)).perform();
		t.tap(PointOption.point(740, 1329)).perform();
		Thread.sleep(2000);
		test.pass("Logged Out", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.PASS, "Logged Out Successfully");
	}

	private static void removeMyAccount(AndroidDriver<AndroidElement> driver, ExtentTest test, By link_menu,
			By link_removeAccount, By link_MyAccount) throws InterruptedException {
		test.log(Status.INFO, "Removing Existing Account Flow");
		Thread.sleep(2000);
		try {
			driver.findElement(link_MyAccount).click();
		} catch (NoSuchElementException e) {
			Thread.sleep(2000);
			test.log(Status.INFO, "Clicking on My Account Option");
			e.printStackTrace();
		}
		driver.findElement(link_menu).click();
		Thread.sleep(2000);
		test.log(Status.INFO, "Clicking on Menu");
		driver.findElement(link_removeAccount).click();
		Thread.sleep(2000);
		test.pass("Account Removed", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.PASS, "Existing account Removed Successfully");
		test.log(Status.INFO, "Test Execution Completed");
		Thread.sleep(2000);
		driver.quit();
	}

	private static String getBase64(AndroidDriver<AndroidElement> driver) {
		TakesScreenshot ts = driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		return source;
	}

}
