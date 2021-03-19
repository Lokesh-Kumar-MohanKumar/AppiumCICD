package com.AppiumJava.Practice;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Demo extends Base {

	@Test
	public void runner() throws MalformedURLException, InterruptedException {
		// Driver Session with Desired Capabilities
		AndroidDriver<AndroidElement> driver = createDriverSessionAndCapabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://www.amazon.com/");

		Thread.sleep(2000);

	}

}
