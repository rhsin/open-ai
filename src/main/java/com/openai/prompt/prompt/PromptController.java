package com.openai.prompt.prompt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class PromptController {

    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/metadata")
    public ResponseEntity<Map<String, String>> getMetadata() {
        return new ResponseEntity(promptService.getMetadata(), HttpStatus.OK);
    }

    @GetMapping("/usage")
    public ResponseEntity<Map<String, String>> getUsage(@RequestParam int month) throws IOException, InterruptedException {
        return new ResponseEntity(promptService.getUsage(month), HttpStatus.OK);
    }

    @GetMapping("/prompt")
    public ResponseEntity<List<PromptRecord>> getPrompts() {
        return new ResponseEntity(promptService.getPrompts(), HttpStatus.OK);
    }

    @PostMapping("/prompt")
    public ResponseEntity<PromptRecord> sendPrompt(@RequestBody String message) throws IOException, InterruptedException {
        return new ResponseEntity(promptService.sendPrompt(message), HttpStatus.OK);
    }
}
