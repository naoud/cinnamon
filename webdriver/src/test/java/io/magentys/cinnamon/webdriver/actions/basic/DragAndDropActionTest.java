package io.magentys.cinnamon.webdriver.actions.basic;

import org.apache.commons.vfs2.provider.webdav.WebdavFileContentInfoFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;

public class DragAndDropActionTest {

    @Mock
    private WebElement mockDraggableElement;
    @Mock
    private WebElement mockDroppableElement;
    @Mock
    private WebDriver mockWebDriver;
    @Mock
    private Coordinates coordinates;

    @Before
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should() {
        DragAndDropAction action = new DragAndDropAction(mockWebDriver, mockDroppableElement);
        action.perform(mockDraggableElement);
    }
}
