package com.openai.prompt.prompt.models;

public class Usage {

    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
    private float total_cost;

    public Usage() {}

    public Usage(int prompt_tokens, int completion_tokens, int total_tokens, float total_cost) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
        this.total_cost = total_cost;
    }

    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setPrompt_tokens(int prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(int completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public int getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(int total_tokens) {
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
