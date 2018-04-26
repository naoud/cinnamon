package io.magentys.cinnamon.webdriver.actions.basic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.actions.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DoubleTapAction implements Action {

    AppiumDriver driver = (AppiumDriver) Browser.getWebDriver();

    public DoubleTapAction(final WebDriver webDriver) {
    }

    public static DoubleTapAction doubleTapAction(WebDriver webDriver) {
        return new DoubleTapAction(webDriver);
    }

    @Override
    public void perform(final WebElement target) {
        new TouchAction(driver).tap(new TapOptions().withTapsCount(2)).perform();
    }
}