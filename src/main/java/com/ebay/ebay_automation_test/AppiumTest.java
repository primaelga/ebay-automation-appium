package com.ebay.ebay_automation_test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {
	public static void main(String[] args) throws InterruptedException {

		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Galaxy Nexus 4 API 27");
		caps.setCapability("udid", "emulator-5554"); // Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "8.1");
		caps.setCapability("appPackage", "com.ebay.mobile");
		caps.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		caps.setCapability("noReset", "false");
		AndroidDriver<MobileElement> driver = null;
		Helper helper = new Helper();
		Boolean res = true;
		int screenHeight = driver.manage().window().getSize().getHeight();
		int screenWidth = driver.manage().window().getSize().getWidth();

		// Instantiate Appium Driver
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			MobileElement elmLeftSide = (MobileElement) driver.findElementById("com.ebay.mobile:id/home");
			elmLeftSide.click();
			MobileElement categoryMenu = (MobileElement) driver
					.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Categories\")"));
			categoryMenu.click();
			//select cell phones & accessories
			MobileElement cellPhonesCategories = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "Cell Phones & Accessories");
			cellPhonesCategories.click();
			//select smart watch categories
			MobileElement smartWatches = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "Smart Watches");
			smartWatches.click();
			//select samsung galaxy product
			MobileElement samsungGalaxy = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "Samsung Galaxy");
			samsungGalaxy.click();
			Thread.sleep(3000);
			//select filter
			MobileElement filterBtn = (MobileElement) driver.findElementById("com.ebay.mobile:id/button_filter");
			filterBtn.click();
			//select filter price
			MobileElement priceFilter = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "Price");
			priceFilter.click();
			
			//select custom price filter 
			MobileElement filterCustomPriceRange = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "Custom price range");
			filterCustomPriceRange.click();
			
			//set min price
			MobileElement minPricetxt = (MobileElement) driver.findElementById("com.ebay.mobile:id/minimum_price_view");
			minPricetxt.click();
			minPricetxt.sendKeys("100");
			
			//set max price
			MobileElement maxPricetxt = (MobileElement) driver.findElementById("com.ebay.mobile:id/maximum_price_view");
			maxPricetxt.click();
			maxPricetxt.sendKeys("500");
			
			//click ok
			MobileElement btnOkCustomPriceFilter = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "OK");
			btnOkCustomPriceFilter.click();
			
			Thread.sleep(3000);
			
			//click button done
			MobileElement btnDoneFilter = (MobileElement) driver.findElementById("com.ebay.mobile:id/button_done");
			btnDoneFilter.click();
			
//			//scroll to result
			
//			
			//$185.99 · Was: $349.00
//			//verify filter result
			
//			MobileElement resultElm = (MobileElement) helper.findScrollElementContainsStringByWait(driver, "Results");
//
//			MobileElement resParent = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView\n" + 
//					"");
			
			helper.swipeUpUntilElementFound(driver, By.id("com.ebay.mobile:id/cell_collection_item"), screenWidth, screenHeight);
			List<MobileElement> resultFilter = driver.findElementsById("com.ebay.mobile:id/cell_collection_item");
			System.out.println(resultFilter);
			for(MobileElement elm:resultFilter) {
				String val = elm.findElementById("com.ebay.mobile:id/textview_primary_0").getText();
				val = val.split(" ")[0].replace("$", "");
				int price = Integer.parseInt(val);
				
				if(price <100 || price > 500) {
					res = false;
				}	
			}
		
			//verify 
//			boolean finishResult = false;
//			int i = 0;
//			while (finishResult == false) {
//				MobileElement resultEach = driver.findelemen
//				
//			}
			
			System.out.print(res);
			Assert.isTrue(res);
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} finally {
			driver.quit();
		}
	}
}
