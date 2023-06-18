package com.openai.prompt.prompt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.prompt.prompt.models.CustomPrompt;
import com.openai.prompt.prompt.models.Message;
import com.openai.prompt.prompt.models.Prompt;
import com.openai.prompt.prompt.models.PromptDTO;
import com.openai.prompt.prompt.models.PromptRecord;
import com.openai.prompt.prompt.models.PromptResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PromptService {

    @Value("${OPENAI_URL}")
    private String apiUrl;
    @Value("${OPENAI_MODEL}")
    private String model;
    @Value("${OPENAI_KEY}")
    private String apiKey;

    private final PromptRecordRepository repository;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper;
    private final PromptMapper promptMapper;

    public PromptService(PromptRecordRepository repository, ObjectMapper mapper, PromptMapper promptMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.promptMapper = promptMapper;
    }

    public Map<String, String> getMetadata() {
        Map<String, String> metadata = new HashMap<>();

        metadata.put("apiUrl", apiUrl);
        metadata.put("model", model);

        return metadata;
    }

    public Map<String, String> getUsage(int month) throws IOException, InterruptedException {
        String end_date = "2023-" + String.format("%02d", month) + "-30";
        Map<String, String> usageData = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl + "/dashboard/billing/usage?start_date=2023-06-01&end_date=" + end_date))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response);

        JsonNode responseJson = mapper.readTree(response.body());

        String totalRequests = String.valueOf(responseJson.get("daily_costs").size());
        String totalCost = String.valueOf(responseJson.get("total_usage").asInt() * 0.000002);

        usageData.put("month", String.format("%02d", month));
        usageData.put("total_requests", totalRequests);
        usageData.put("total_usage", responseJson.get("total_usage").asText());
        usageData.put("total_cost", totalCost);
        System.out.println("Usage data: " + usageData.toString());

        return usageData;
    }

    public List<PromptRecord> getPromptRecords() {
        return repository.findAll();
    }

    public List<PromptDTO> getPromptDTOs() {
        List<PromptRecord> prompts = repository.findAll();
        List<PromptDTO> promptDTOs = promptMapper.mapPromptDTOs(prompts);

        return promptDTOs;
    }

    public PromptRecord getPromptRecord(Long id) throws ResponseStatusException {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prompt record not found: " + id));
    }

    public List<PromptDTO> findPromptDTOs(String field, String keyword) {
        List<PromptRecord> prompts = repository.findAll();
        List<PromptDTO> promptDTOs = promptMapper.mapPromptDTOs(prompts);

        switch (field) {
            case "prompt":
                System.out.println("Searching prompts for: " + keyword);
                return promptDTOs.stream()
                    .filter(prompt -> prompt.getPrompts().stream()
                    .anyMatch(p -> p.toLowerCase().contains(keyword.toLowerCase())))
                    .toList();
            case "response":
                System.out.println("Searching responses for: " + keyword);
                return promptDTOs.stream()
                    .filter(prompt -> prompt.getResponses().stream()
                    .anyMatch(p -> p.toLowerCase().contains(keyword.toLowerCase())))
                    .toList();
            case "prompt_id":
                System.out.println("Searching prompt ids for: " + keyword);
                return promptDTOs.stream()
                    .filter(prompt -> prompt.getPrompt_id().contains(keyword))
                    .toList();
            default:
                System.out.println("Searching prompts for: " + keyword);
                return promptDTOs.stream()
                    .filter(prompt -> prompt.getPrompts().stream()
                    .anyMatch(p -> p.toLowerCase().contains(keyword.toLowerCase())))
                    .toList();
        }
    }

    public PromptRecord sendDefaultPrompt(String message) throws IOException, InterruptedException {
        List<Message> messages = new ArrayList<>();
        Message newMessage = new Message("user", message);
        messages.add(newMessage);

        return sendPrompt(model, messages, 15, 1);
    }

    public PromptRecord sendCustomPrompt(CustomPrompt customPrompt) throws IOException, InterruptedException {
        System.out.println(customPrompt);
        List<Message> messages = new ArrayList<>();
        String customMessage = "Can you provide some guidance on implementing " + customPrompt.getCategory() +
            " standards in " + customPrompt.getIndustry() + "?";

        Message newMessage = new Message("user", customMessage);
        Message nextMessage = new Message("user", customPrompt.getMessage());
        messages.add(newMessage);
        messages.add(nextMessage);

        return sendPrompt(model, messages, 30, .5);
    }

    private PromptRecord sendPrompt(String model, List<Message> messages, int max_tokens, double temperature) 
        throws IOException, InterruptedException {
        Prompt newPrompt = new Prompt(model, messages, max_tokens, temperature);
        System.out.println(newPrompt.toString());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl + "/chat/completions"))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(newPrompt)))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response);
        System.out.println("Response body: " + response.body());

        PromptResponse promptResponse = mapper.readValue(response.body(), PromptResponse.class);
        JsonNode responseJson = mapper.readTree(response.body());
        String id = responseJson.get("id").asText();
        promptResponse.setPromptId(id);
        System.out.println(promptResponse.toString());

        PromptRecord promptRecord = promptMapper.mapPromptRecord(newPrompt, promptResponse);
        System.out.println(promptRecord.toString());

        return repository.save(promptRecord);
    }
}
