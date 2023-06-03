package com.openai.prompt.prompt;

public class Choice {

    private String text;
    private Integer index;
    private Integer logprobs;
    private String finish_reason;

    public Choice(String text, Integer index, Integer logprobs, String finish_reason) {
        this.text = text;
        this.index = index;
        this.logprobs = logprobs;
        this.finish_reason = finish_reason;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Integer logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    @Override
    public String toString() {
        return "Choice{" +
            "text='" + text + '\'' +
            ", index=" + index +
            ", logprobs=" + logprobs +
            ", finish_reason='" + finish_reason + '\'' +
            '}';
    }
}
