package com.openai.prompt.prompt.models;

import java.util.List;

public class Prompt {

    private String model;
    private List<Message> messages;
    private int max_tokens;
    private double temperature;

    public Prompt() {}

    public Prompt(String model, List<Message> messages, int max_tokens, double temperature) {
        this.model = model;
        this.messages = messages;
        this.max_tokens = max_tokens;
        this.temperature = temperature;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Prompt{" +
            ", model='" + model + '\'' +
            ", messages=" + messages +
            ", max_tokens=" + max_tokens +
            ", temperature=" + temperature +
            '}';
    }
}
