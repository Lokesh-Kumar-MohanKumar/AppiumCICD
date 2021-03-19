package com.AppiumJava.Practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AttachScreenshots {

	// 1. Take a screenshot as png, jpg file ---> Attach to report
	// 2. Take a screenshot as png, jpg file ---> Convert it to base64.
	// 3. Take a screenshot as base64 ---> Attach to report

	/*
	 * public static String getScreenshot(AndroidDriver<AndroidElement> driver)
	 * throws IOException { File source = ((TakesScreenshot)
	 * driver).getScreenshotAs(OutputType.FILE); String path =
	 * System.getProperty("user.dir") + "/Screenshot" + System.currentTimeMillis() +
	 * ".png"; FileUtils.copyFile(source, new File(path)); return path; }
	 */

	ExtentReports extent;
	static AndroidDriver<AndroidElement> driver;

	public static String getScreenShotPath(AndroidDriver<AndroidElement> driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot/image.png";
		FileUtils.copyFile(source, new File(path));
		return path;
	}

	public static String getScreenShotAsBase64(AndroidDriver<AndroidElement> driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot/image.png";
		FileUtils.copyFile(source, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	public static String getBase64(AndroidDriver<AndroidElement> driver) {
		TakesScreenshot ts = driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		return source;
	}

}
