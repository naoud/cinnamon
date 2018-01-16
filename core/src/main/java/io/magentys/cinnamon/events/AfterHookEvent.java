package io.magentys.cinnamon.events;

public interface AfterHookEvent {

    boolean isFailed();

    String getStatus();

    String getErrorMessage();

    Throwable getError();
}
