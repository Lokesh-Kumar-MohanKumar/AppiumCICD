package com.AppiumJava.Practice;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class AnyScenarioAssignment extends Base {

	@Test
	public static void RunnerMethod() throws MalformedURLException, InterruptedException {

		// Driver Session with Desired Capabilities
		AndroidDriver<AndroidElement> driver = createDriverSessionAndCapabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Objects Identification
		By upgradeOkButton = By.xpath("//android.widget.Button[@text='OK']");
		By aboutOkButton = By.xpath("//android.widget.Button[@text='Ok']");
		By androidAuto = By.xpath("//android.widget.TextView[@text='Android Auto']");
		By androidSystemWebView = By.xpath("//android.widget.TextView[@text='Android System WebView']");
		By calendarStorage = By.xpath("//android.widget.TextView[@text='Calendar Storage']");
		By carrierServices = By.xpath("//android.widget.TextView[@text='Carrier Services']");
		By chrome = By.xpath("//android.widget.TextView[@text='Chrome']");
		By openButton = By.id("com.fortysevendeg.android.swipelistview:id/example_row_b_action_1");
		By facebookIcon = By.xpath("//android.widget.TextView[@text='Facebook – log in or sign up']");
		By fbUsername = By.className("android.widget.EditText");
		By fbPassword = By.className("android.widget.EditText");
		By loginButton = By.xpath("//android.widget.Button[@text='Log In']");

		// Skipping "Upgrade" and "About" option
		userUpgradeAndAboutOptions(driver, upgradeOkButton, aboutOkButton);

		// Swiping to Chrome browser
		TouchAction t = swipeToChrome(driver, androidAuto, androidSystemWebView, calendarStorage, carrierServices);

		// Opening Chrome Browser
		openChrome(driver, chrome, openButton, t);

		// FaceBook Login Flow
		fbLoginFlow(driver, facebookIcon, fbUsername, fbPassword, loginButton);

	}

	// Functions For Skipping "Upgrade" and "About" option
	private static void userUpgradeAndAboutOptions(AndroidDriver<AndroidElement> driver, By upgradeOkButton,
			By aboutOkButton) {
		driver.findElement(upgradeOkButton).click();
		driver.findElement(aboutOkButton).click();
	}

	// Functions For Swiping to Chrome browser
	private static TouchAction swipeToChrome(AndroidDriver<AndroidElement> driver, By androidAuto,
			By androidSystemWebView, By calendarStorage, By carrierServices) {

		TouchAction t = new TouchAction(driver);
		t.press(ElementOption.element(driver.findElement(androidSystemWebView)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(ElementOption.element(driver.findElement(androidAuto))).release().perform();

		t.press(ElementOption.element(driver.findElement(calendarStorage)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(ElementOption.element(driver.findElement(androidSystemWebView))).release().perform();

		t.press(ElementOption.element(driver.findElement(carrierServices)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
				.moveTo(ElementOption.element(driver.findElement(calendarStorage))).release().perform();
		return t;
	}

	// Functions For Opening Chrome Browser
	private static void openChrome(AndroidDriver<AndroidElement> driver, By chrome, By openButton, TouchAction t) {
		t.longPress(ElementOption.element(driver.findElement(chrome)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).release().perform();
		try {
			driver.findElements(openButton).get(2).click();
		} catch (Exception e) {
			driver.findElements(openButton).get(3).click();
			e.printStackTrace();
		}
		// driver.findElements(openButton).get(2).click();
	}

	// Functions For FaceBook Login Flow
	private static void fbLoginFlow(AndroidDriver<AndroidElement> driver, By facebookIcon, By fbUsername, By fbPassword,
			By loginButton) throws InterruptedException {
		driver.findElement(facebookIcon).click();
		Thread.sleep(5000);
		driver.findElements(fbUsername).get(0).setValue("lokeshdon95@yahoo.com");
		driver.findElements(fbPassword).get(1).sendKeys("lokeshcool11");
		Thread.sleep(2000);
		driver.findElement(loginButton).click();
	}

}
