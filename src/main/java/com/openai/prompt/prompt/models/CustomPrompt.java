package com.openai.prompt.prompt.models;

public class CustomPrompt {
  
  private String category;
  private String industry;
  private String message;

  public CustomPrompt() {}

  public CustomPrompt(String category, String industry, String message) {
    this.category = category;
    this.industry = industry;
    this.message = message;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "CustomPrompt [category=" + category + ", industry=" + industry + ", message=" + message + "]";
  }
}
