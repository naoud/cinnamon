package io.magentys.cinnamon.webdriver.actions.synthetic;


import io.magentys.cinnamon.webdriver.actions.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SyntheticDragAndDropAction implements Action {

    private final SyntheticEvent syntheticEvent;
    private final WebElement destinationElement;

    public SyntheticDragAndDropAction(final WebDriver webDriver, final WebElement destinationElement) {
        this.syntheticEvent = new SyntheticEvent(webDriver);
        this.destinationElement = destinationElement;
    }

    public static SyntheticDragAndDropAction syntheticDragAndDropAction(final WebDriver webDriver, final WebElement destinationElement) {
        return new SyntheticDragAndDropAction(webDriver, destinationElement);
    }

    @Override
    public void perform(WebElement target) {
        syntheticEvent
                .fireEvent(target, DomEvent.MOUSEMOVE)
                .fireEvent(target, DomEvent.MOUSEDOWN)
                .fireEvent(destinationElement, DomEvent.MOUSEMOVE)
                .fireEvent(destinationElement, DomEvent.MOUSEUP);
    }
}