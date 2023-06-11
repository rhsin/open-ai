package com.openai.prompt.prompt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.openai.prompt.prompt.models.CustomPrompt;
import com.openai.prompt.prompt.models.PromptDTO;
import com.openai.prompt.prompt.models.PromptRecord;

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
        return new ResponseEntity<>(promptService.getMetadata(), HttpStatus.OK);
    }

    @GetMapping("/usage")
    public ResponseEntity<Map<String, String>> getUsage(@RequestParam int month) throws IOException, InterruptedException {
        return new ResponseEntity<>(promptService.getUsage(month), HttpStatus.OK);
    }

    @GetMapping("/prompt")
    public ResponseEntity<List<PromptDTO>> getPromptDTOs() throws IOException, InterruptedException {
        return new ResponseEntity<>(promptService.getPromptDTOs(), HttpStatus.OK);
    }

    @GetMapping("/prompt/records")
    public ResponseEntity<List<PromptRecord>> getPromptRecords() {
        return new ResponseEntity<>(promptService.getPromptRecords(), HttpStatus.OK);
    }

    @GetMapping("/prompt/records/{id}")
    public ResponseEntity<PromptRecord> getPromptRecord(@PathVariable Long id) throws ResponseStatusException {
        return new ResponseEntity<>(promptService.getPromptRecord(id), HttpStatus.OK);
    }

    @GetMapping("/prompt/find/{field}")
    public ResponseEntity<List<PromptDTO>> findPromptDTOs(@PathVariable String field, @RequestParam String keyword) {
        return new ResponseEntity<>(promptService.findPromptDTOs(field, keyword), HttpStatus.OK);
    }

    @PostMapping("/prompt")
    public ResponseEntity<PromptRecord> sendDefaultPrompt(@RequestBody String message) throws IOException, InterruptedException {
        return new ResponseEntity<>(promptService.sendDefaultPrompt(message), HttpStatus.CREATED);
    }

    @PostMapping("/prompt/custom")
    public ResponseEntity<PromptRecord> sendCustomPrompt(@RequestBody CustomPrompt customPrompt) 
        throws IOException, InterruptedException {
        return new ResponseEntity<>(promptService.sendCustomPrompt(customPrompt), HttpStatus.CREATED);
    }
}
