package io.magentys.cinnamon.webdriver.actions.basic;

import io.magentys.cinnamon.webdriver.Browser;
import io.magentys.cinnamon.webdriver.actions.Action;
import io.magentys.cinnamon.webdriver.actions.synthetic.DomEvent;
import io.magentys.cinnamon.webdriver.actions.synthetic.SyntheticEvent;
import io.magentys.cinnamon.webdriver.conditions.AjaxFinishedCondition;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

public class DragAndDropAction implements Action {
    private final WebElement destinationElement;
    private final WebDriver webDriver;

    public DragAndDropAction(final WebDriver webDriver, final WebElement destinationElement) {
        this.destinationElement = destinationElement;
        this.webDriver = webDriver;
    }

    public static DragAndDropAction dragAndDropAction(final WebDriver webDriver, final WebElement destinationElement) {
        return new DragAndDropAction(webDriver, destinationElement);
    }

    @Override
    public void perform(final WebElement target) {
        Coordinates targetCoords = ((Locatable) target).getCoordinates();
        Coordinates destinationCoords = ((Locatable) destinationElement).getCoordinates();
        Mouse mouse = ((HasInputDevices) webDriver).getMouse();
        mouse.mouseMove(targetCoords);
        mouse.mouseDown(targetCoords);
        mouse.mouseMove(destinationCoords);
        mouse.mouseUp(destinationCoords);
    }
}
