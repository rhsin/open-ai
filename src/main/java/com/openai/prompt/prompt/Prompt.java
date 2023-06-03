package com.openai.prompt.prompt;

import jakarta.persistence.*;

//@Entity
//@Table(name = "prompts")
public class Prompt {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;
    private String prompt;
    private int maxTokens;
    private double temperature;

    public Prompt() {}

    public Prompt(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }

    public Prompt(String model, String prompt, int maxTokens, double temperature) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
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
            "id=" + id +
            ", model='" + model + '\'' +
            ", prompt='" + prompt + '\'' +
            ", maxTokens=" + maxTokens +
            ", temperature=" + temperature +
            '}';
    }
}
