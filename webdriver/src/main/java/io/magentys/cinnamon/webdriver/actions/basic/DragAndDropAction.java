package io.magentys.cinnamon.webdriver.actions.basic;

import io.magentys.cinnamon.webdriver.actions.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;


public class DragAndDropAction implements Action {
    private final WebDriver webDriver;
    private final WebElement destinationElement;

    public DragAndDropAction(final WebDriver webDriver, final WebElement destinationElement) {
        this.webDriver = webDriver;
        this.destinationElement = destinationElement;
    }

    public static DragAndDropAction dragAndDropAction(final WebDriver webDriver, final WebElement destinationElement) {
        return new DragAndDropAction(webDriver, destinationElement);
    }

    @Override
    public void perform(final WebElement target) {
        final Locatable dragItem = (Locatable) target;
        final Locatable destination = (Locatable) destinationElement;
        final Mouse mouse = ((HasInputDevices) webDriver).getMouse();
        mouse.mouseDown(dragItem.getCoordinates());
        mouse.mouseUp(destination.getCoordinates());
    }
}
