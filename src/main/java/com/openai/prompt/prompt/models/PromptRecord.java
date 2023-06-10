package com.openai.prompt.prompt.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prompt_records")
public class PromptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int max_tokens;
    private double temperature;
    private String prompt_id;
    private String object;
    private LocalDate created;
    private String model;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="JSON")
    private List<Message> messages;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="JSON")
    private List<Choice> choices;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="JSON")
    private Usage usage;

    public PromptRecord() {}

    public PromptRecord(int max_tokens, double temperature, String prompt_id, String object, LocalDate created, String model, List<Message> messages, List<Choice> choices, Usage usage) {
        this.max_tokens = max_tokens;
        this.temperature = temperature;
        this.prompt_id = prompt_id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.messages = messages;
        this.choices = choices;
        this.usage = usage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPrompt_id() {
        return prompt_id;
    }

    public void setPrompt_id(String prompt_id) {
        this.prompt_id = prompt_id;
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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
        return "PromptRecord{" +
            "id=" + id +
            ", max_tokens=" + max_tokens +
            ", temperature=" + temperature +
            ", prompt_id='" + prompt_id + '\'' +
            ", object='" + object + '\'' +
            ", created=" + created +
            ", model='" + model + '\'' +
            ", messages=" + messages +
            ", choices=" + choices +
            ", usage=" + usage +
            '}';
    }
}
