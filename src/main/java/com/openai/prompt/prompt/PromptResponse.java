package com.openai.prompt.prompt;

import java.time.LocalDate;
import java.util.List;

public class PromptResponse {

    private String promptId;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    public PromptResponse() {}

    public PromptResponse(String object, LocalDate created, String model, List<Choice> choices, Usage usage) {
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
    }

    public PromptResponse(String promptId, String object, LocalDate created, String model, List<Choice> choices, Usage usage) {
        this.promptId = promptId;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
    }

    public String getPromptId() {
        return promptId;
    }

    public void setPromptId(String promptId) {
        this.promptId = promptId;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "PromptResponse{" +
            ", promptId='" + promptId + '\'' +
            ", object='" + object + '\'' +
            ", created=" + created +
            ", model='" + model + '\'' +
            ", choices=" + choices +
            ", usage=" + usage +
            '}';
    }
}
