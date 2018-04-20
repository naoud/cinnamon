package io.magentys.cinnamon.webdriver.actions;

import org.openqa.selenium.WebElement;

public interface DurationAction extends GenericAction<WebElement> {

    void perform(WebElement target, int duration);
}