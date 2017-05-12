package com.acme.samples.local.pages.mouse;


import io.magentys.cinnamon.webdriver.elements.PageElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPage {

    @FindBy(id = "draggable")
    private PageElement draggable;

    @FindBy(id = "droppable")
    private PageElement droppable;

    public void dragTargetToDestination() {
        draggable.dragAndDrop(droppable);
    }

    public String getDroppableText() {
        return droppable.getText();
    }
}
