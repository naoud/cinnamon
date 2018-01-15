package io.magentys.cinnamon.events;

public interface TestStepFinishedCucumberEvent extends StatusEvent, AttachmentEvent {

    String getErrorMessage();

    Throwable getError();

    boolean isFailed();

    String getStatus();

    void attach(Attachment attachment);
}
