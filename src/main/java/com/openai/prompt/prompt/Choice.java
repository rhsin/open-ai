package com.openai.prompt.prompt;

public class Choice {

    private Message message;
    private Integer index;
    private String finish_reason;

    public Choice() {}

    public Choice(Message message, Integer index, String finish_reason) {
        this.message = message;
        this.index = index;
        this.finish_reason = finish_reason;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}
