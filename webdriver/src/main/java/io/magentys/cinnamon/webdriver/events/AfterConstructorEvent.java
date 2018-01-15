package io.magentys.cinnamon.webdriver.events;

import io.magentys.cinnamon.events.ConstructorEvent;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AfterConstructorEvent implements ConstructorEvent {

    private final WebDriver webDriver;
    private final String featureName;
    private final String scenarioName;
    private final List<String> tagsOfScenario;

    public AfterConstructorEvent(final WebDriver webDriver, final String featureName, String scenarioName, List<String> tagsOfScenario) {
        this.webDriver = webDriver;
        this.featureName = featureName;
        this.scenarioName = scenarioName;
        this.tagsOfScenario = tagsOfScenario;
    }



    @Override
    public WebDriver getThis() {
        return webDriver;
    }


    @Override
    public String getFeatureName() {
        return featureName;
    }

    @Override
    public String getScenarioName() {
        return scenarioName;
    }

    @Override
    public List<String> getTagsOfScenario() {
        return tagsOfScenario;
    }
}
