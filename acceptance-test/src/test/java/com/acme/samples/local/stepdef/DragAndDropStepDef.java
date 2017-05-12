package com.acme.samples.local.stepdef;


import com.acme.samples.local.pages.mouse.DragAndDropPage;
import cucumber.api.java.en.When;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DragAndDropStepDef {

    private final DragAndDropPage page;

    @Inject
    public DragAndDropStepDef(final DragAndDropPage dragAndDropPage){
        this.page = dragAndDropPage;
    }


    @When("^I drag the target element to the destination element$")
    public void i_drag_target_element_to_destination() throws Throwable {
        page.dragTargetToDestination();
    }

    @When("^I should see \"Dropped\" in the destination element$")
    public void i_should_see_in_the_destination_element() throws Throwable {
        assertThat(page.getDroppableText(), equalTo("Dropped!"));
    }
}
