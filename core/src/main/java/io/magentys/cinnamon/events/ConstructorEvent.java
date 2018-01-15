package io.magentys.cinnamon.events;

import java.util.List;

public interface ConstructorEvent {

    <T> T getThis();

    String getFeatureName();

    String getScenarioName();

    List<String> getTagsOfScenario();
}
