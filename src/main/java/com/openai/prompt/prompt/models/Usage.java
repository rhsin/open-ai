package com.openai.prompt.prompt.models;

public class Usage {

    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
    private float total_cost;

    public Usage() {}

    public Usage(Integer prompt_tokens, Integer completion_tokens, Integer total_tokens, float total_cost) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
        this.total_cost = total_cost;
    }

    public Integer getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setPrompt_tokens(Integer prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public Integer getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(Integer completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public Integer getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(Integer total_tokens) {
        this.total_tokens = total_tokens;
    }

    public float getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(float total_cost) {
        this.total_cost = total_cost;
    }

    @Override
    public String toString() {
        return "Usage{" +
            "prompt_tokens=" + prompt_tokens +
            ", completion_tokens=" + completion_tokens +
            ", total_tokens=" + total_tokens +
            ", total_cost=" + total_cost +
            '}';
    }
}
