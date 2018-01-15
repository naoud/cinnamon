package io.magentys.cinnamon.cucumber.events;

import io.magentys.cinnamon.events.TagEvent;

import java.util.List;

public class TagsEvent implements TagEvent {
    List<String> tags;

    public TagsEvent(List<String> tags) {
        this.tags=tags;
    }

    public List<String> getTags() {
        return tags;
    }
}
