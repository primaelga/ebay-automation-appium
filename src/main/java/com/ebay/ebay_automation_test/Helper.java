package com.ebay.ebay_automation_test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {
	
	public static WebElement findScrollElementContainsStringByWait(WebDriver driver, String str) {
		WebElement elm=null;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String uiSelector = "new UiSelector().textMatches(\"" + str
                + "\")";

		String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                + uiSelector + ");";
		elm = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(command)));
		return elm;
	}
	
	public static void swipeUpUntilElementFound(AndroidDriver<MobileElement> driver,By id, int screenWidth, int screenHeight) {
		TouchAction ts = new TouchAction(driver);
		int startX = (int) (screenWidth * 0.5);
		int startY = (int) (screenHeight * 0.6);
		int endX = (int) (screenWidth * 0.5);
		int endY = (int) (screenHeight * 0.3);
		int iter = 0;
		ts.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
		while (driver.findElements(id).size() == 0) {
			ts.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
			iter++;
			if (iter > 19) {
				new Exception("Element not found after 20 time scrolling or Something wrong happened with scrolling");
				break;
			}

			endY = (int) (screenHeight * 0.45);
			ts.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();

		}
	}

}
