package com.openai.prompt.prompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openai.prompt.prompt.models.Prompt;
import com.openai.prompt.prompt.models.PromptDTO;
import com.openai.prompt.prompt.models.PromptRecord;
import com.openai.prompt.prompt.models.PromptResponse;

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

    public PromptDTO mapPromptDTO(PromptRecord promptRecord) {
        List<String> prompts = promptRecord.getMessages().stream()
            .map(message -> message.getContent())
            .toList();
        List<String> responses = promptRecord.getChoices().stream()
            .map(choice -> choice.getMessage().getContent())
            .toList();

        PromptDTO promptDTO = new PromptDTO(
            promptRecord.getPrompt_id(),
            promptRecord.getCreated(),
            promptRecord.getModel(),
            prompts,
            responses,
            promptRecord.getUsage().getTotal_tokens()
        );

        return promptDTO;
    }

    public List<PromptDTO> mapPromptDTOs(List<PromptRecord> promptRecords) {
        List<PromptDTO> promptDTOs = new ArrayList<>();

        for (PromptRecord promptRecord : promptRecords) {
            promptDTOs.add(mapPromptDTO(promptRecord));
        }

        return promptDTOs;
    }
}
