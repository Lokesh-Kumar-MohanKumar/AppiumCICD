package com.AppiumJava.Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	static ExtentReports extent;
	static AndroidDriver<AndroidElement> driver;

	public static AndroidDriver<AndroidElement> createDriverSessionAndCapabilities()
			throws MalformedURLException, InterruptedException {

		DesiredCapabilities caps = new DesiredCapabilities(); // Driver Session
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_XL_API_30");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
//		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100000);
//		caps.setCapability("avd", "Pixel_3");  //Automatic Opening of Emulator
//		caps.setCapability("avdLaunchTimeout", 180000);
//		caps.setCapability("appPackage", "com.android.chrome");
//		caps.setCapability("appActivity", "org.chromium.chrome.browser.document.ChromeLauncherActivity");
		caps.setCapability("chromedriverExecutable",
				"C:\\Users\\RT531JA\\OneDrive - EY\\Documents\\PracticeAppium\\drivers\\chromedriver.exe");
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//		String appPath = "C:\\Users\\RT531JA\\OneDrive - EY\\Documents\\PracticeAppium\\resources\\facebook-310-0-0-0-83.apk";
//		caps.setCapability(MobileCapabilityType.APP, appPath);

		URL url = new URL("http://0.0.0.0:4723/wd/hub");

		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(url, caps);
		return driver;

	}

}
