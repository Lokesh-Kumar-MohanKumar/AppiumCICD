package com.AppiumJava.Practice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class FacebookAppAssignment1 extends Base {

	@BeforeTest
	public void setUp() throws IOException {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("FacebookAppMobileTestReport.html");
		spark.config().setDocumentTitle("Mobile Automation Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Facebook App Mobile Automation Report");
		extent.attachReporter(spark);
	}

	@Test
	public static void facebookAppRunner() throws MalformedURLException, InterruptedException {

		// Driver Session with Desired Capabilities
		AndroidDriver<AndroidElement> driver = createDriverSessionAndCapabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TouchAction t = new TouchAction(driver);
		ExtentTest test = extent
				.createTest("Verify the User can able to do Login and Logout flow using Facebook Application")
				.assignAuthor("Lokesh").assignCategory("App").assignDevice("MobileApp");
		test.info("Test Started");

		// Objects
		By textbox_userName = By.xpath("//android.widget.EditText[@content-desc=\"Username\"]");
		By textbox_password = By.xpath("//android.widget.EditText[@content-desc=\"Password\"]");
		By button_login = By.xpath("//android.view.ViewGroup[@content-desc=\"Log In\"]");
		By link_notNowLoginInfo = By.xpath("//android.widget.Button[@text='Not Now']");
		By link_skipFindFriends = By.xpath("//android.widget.Button[@text='Skip']");
		By link_denyAccess = MobileBy.AccessibilityId("Deny");
		By icon_homePage = By.xpath("//android.view.ViewGroup[@content-desc=\"Go to profile\"]");
		By link_logout = By.xpath("//android.view.ViewGroup[@content-desc=\"Log Out, Button 1 of 1\"]");
		By link_close = By.xpath("//android.view.ViewGroup[@content-desc=\"Close\"]");
		By link_menu = By.xpath("//android.widget.Button[@content-desc=\"Menu\"]");
		By link_removeAccount = By.xpath("//android.widget.TextView[@text='Remove account from device']");
		By link_removeAccountConfirm = By.xpath("//android.widget.Button[@text='REMOVE ACCOUNT']");

		// Login Flow
		loginFlow(driver, textbox_userName, textbox_password, button_login, link_notNowLoginInfo, link_skipFindFriends,
				link_denyAccess, icon_homePage, t, test);

		// Swipe through News Feed
		swipeFeed(t, test);

		// Logout Flow
		facebookLogoutFlow(driver, link_logout, t, test);

		// Removing Exiting Account
		removeLoggedInAccount(driver, link_close, link_menu, link_removeAccount, link_removeAccountConfirm, test);
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
	}

	private static void loginFlow(AndroidDriver<AndroidElement> driver, By textbox_userName, By textbox_password,
			By button_login, By link_notNowLoginInfo, By link_skipFindFriends, By link_denyAccess, By icon_homePage,
			TouchAction t, ExtentTest test) throws InterruptedException {
		Thread.sleep(5000);
		test.pass("Facebook Login Page Opened",
				MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.INFO, "Successfully Opened Facebook Login Page");
		driver.findElement(textbox_userName).sendKeys("lokeshdon95@yahoo.com");
		test.log(Status.INFO, "Username Accepted");
		driver.findElement(textbox_password).sendKeys("lokeshcool11");
		Thread.sleep(2000);
		test.log(Status.INFO, "Passed Accepted");
		driver.findElement(button_login).click();
		Thread.sleep(30000);
		test.log(Status.INFO, "Looged In");
		driver.findElement(link_notNowLoginInfo).click();
		Thread.sleep(3000);
		test.log(Status.INFO, "Clicked Not Now Link");
		t.tap(PointOption.point(860, 1400)).perform();
		Thread.sleep(2000);
		driver.findElement(link_skipFindFriends).click();
		Thread.sleep(5000);
		test.log(Status.INFO, "Clicked on Skip Link");
		driver.findElement(link_denyAccess).click();
		Thread.sleep(5000);
		test.log(Status.INFO, "Clicked on Access Deny Link");
		driver.findElement(icon_homePage).isDisplayed();
		test.pass("Home Page Displayed",
				MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.INFO, "Home Page Displayed");
	}

	private static void swipeFeed(TouchAction t, ExtentTest test) throws InterruptedException {
		Thread.sleep(2000);
		swipeUp(t);
		Thread.sleep(2000);
		test.log(Status.INFO, "Swipped Up");
		Thread.sleep(2000);
		swipeDown(t);
		test.log(Status.INFO, "Swipped Down");
		test.log(Status.INFO, "Swipe Done Successfully on Feed");
		Thread.sleep(5000);
	}

	private static void facebookLogoutFlow(AndroidDriver<AndroidElement> driver, By link_logout, TouchAction t,
			ExtentTest test) throws InterruptedException {
		t.tap(PointOption.point(1311, 343)).perform();
		Thread.sleep(5000);
		test.log(Status.INFO, "Clicked On Main Menu Link");
		swipeUp(t);
		Thread.sleep(2000);
		test.log(Status.INFO, "Swipped Up");
		driver.findElement(link_logout).click();
		Thread.sleep(2000);
		test.pass("Logged Out", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.INFO, "Logged Out Successfully");
	}

	private static void removeLoggedInAccount(AndroidDriver<AndroidElement> driver, By link_close, By link_menu,
			By link_removeAccount, By link_removeAccountConfirm, ExtentTest test) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(link_close).click();
		test.log(Status.INFO, "Closed PopUp");
		driver.findElement(link_menu).click();
		test.log(Status.INFO, "Clicked Menu Icon");
		driver.findElement(link_removeAccount).click();
		test.log(Status.INFO, "Clicked on Remove Account Link");
		driver.findElement(link_removeAccountConfirm).click();
		Thread.sleep(2000);
		test.pass("Account Removed", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		test.log(Status.INFO, "Account Removed Successfully");
		driver.quit();
	}

	private static void swipeDown(TouchAction t) {
		t.press(PointOption.point(440, 440)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(PointOption.point(400, 1950)).release().perform();
	}

	private static void swipeUp(TouchAction t) {
		t.press(PointOption.point(400, 1950)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(PointOption.point(440, 440)).release().perform();
	}

	private static String getBase64(AndroidDriver<AndroidElement> driver) {
		TakesScreenshot ts = driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		return source;
	}

}
