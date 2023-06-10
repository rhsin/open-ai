package com.openai.prompt.prompt.models;

import java.time.LocalDate;
import java.util.List;

public class PromptDTO {

  private String prompt_id;
  private LocalDate created;
  private String model;
  private List<String> prompts;
  private List<String> responses;
  private Integer total_tokens;

  public PromptDTO() {}

  public PromptDTO(String prompt_id, LocalDate created, String model, List<String> prompts, List<String> responses, Integer total_tokens) {
    this.prompt_id = prompt_id;
    this.created = created;
    this.model = model;
    this.prompts = prompts;
    this.responses = responses;
    this.total_tokens = total_tokens;
  }

  public String getPrompt_id() {
    return prompt_id;
  }

  public void setPrompt_id(String prompt_id) {
    this.prompt_id = prompt_id;
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

  public List<String> getPrompts() {
    return prompts;
  }

  public void setPrompts(List<String> prompts) {
    this.prompts = prompts;
  }

  public List<String> getResponses() {
    return responses;
  }

  public void setResponses(List<String> responses) {
    this.responses = responses;
  }

  public Integer getTotal_tokens() {
    return total_tokens;
  }

  public void setTotal_tokens(Integer total_tokens) {
    this.total_tokens = total_tokens;
  }

  @Override
  public String toString() {
    return "PromptDTO [prompt_id=" + prompt_id + ", created=" + created + ", model=" + model + ", prompts=" + prompts
      + ", responses=" + responses + ", total_tokens=" + total_tokens + "]";
  }
}