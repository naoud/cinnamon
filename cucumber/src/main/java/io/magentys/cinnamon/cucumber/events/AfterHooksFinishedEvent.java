package io.magentys.cinnamon.cucumber.events;

import gherkin.formatter.model.Result;
import io.magentys.cinnamon.events.AfterHookEvent;

public class AfterHooksFinishedEvent implements AfterHookEvent {
    private final Result result;
    private final String errorMessage;
    private final Throwable error;

    public AfterHooksFinishedEvent(final Result result, final String errorMessage, final Throwable error) {
        this.result=result;
        this.error=error;
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Throwable getError() {
        return this.error;
    }

    @Override
    public boolean isFailed() {
        return "failed".equals(getStatus());
    }

    @Override
    public String getStatus() {
        return result.getStatus();
    }
}
