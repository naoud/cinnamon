package io.magentys.cinnamon.cucumber.events;

import io.magentys.cinnamon.events.BeforeHookEvent;

public class BeforeHooksRunEvent implements BeforeHookEvent {

    private final String stepName;

    public BeforeHooksRunEvent(final String stepName) {
        this.stepName = stepName;
    }

    @Override
    public String getName() {
        return stepName;
    }
}
