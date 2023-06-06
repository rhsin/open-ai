package com.openai.prompt.prompt;

import org.springframework.stereotype.Service;

@Service
public class PromptMapper {

    public PromptRecord mapPromptRecord(Prompt prompt, PromptResponse promptResponse) {
        PromptRecord promptRecord = new PromptRecord(
            prompt.getMax_tokens(),
            prompt.getTemperature(),
            promptResponse.getPromptId(),
            promptResponse.getObject(),
            promptResponse.getCreated(),
            promptResponse.getModel(),
            prompt.getMessages(),
            promptResponse.getChoices(),
            promptResponse.getUsage()
        );

        return promptRecord;
    }
}
