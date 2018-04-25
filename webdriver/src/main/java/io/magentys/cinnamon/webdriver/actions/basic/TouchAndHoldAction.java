package io.magentys.cinnamon.webdriver.actions.basic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.actions.DurationAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class TouchAndHoldAction implements DurationAction
{
    AppiumDriver driver = (AppiumDriver) Browser.getWebDriver();

    public TouchAndHoldAction(final WebDriver webDriver) {
    }

    public static TouchAndHoldAction touchAndHoldAction(WebDriver webDriver) {
        return new TouchAndHoldAction(webDriver);
    }


    @Override
    public void perform(WebElement target, int duration) {
        new TouchAction(driver).longPress(new LongPressOptions()
                .withElement(new ElementOption().withElement(target))
                .withDuration(Duration.ofSeconds(duration))).perform();
    }

    @Override
    public void perform(WebElement target) {
        perform(target, 5000);
    }
}
