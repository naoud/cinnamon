package io.magentys.cinnamon.webdriver.actions.basic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.actions.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TapAction implements Action {
    AppiumDriver driver = (AppiumDriver) Browser.getWebDriver();

    public TapAction(final WebDriver webDriver) {
    }

    public static TapAction tapAction(WebDriver webDriver) {
        return new TapAction(webDriver);
    }

    @Override
    public void perform(final WebElement target) {
        new TouchAction(driver).tap(target).perform();
    }


}
