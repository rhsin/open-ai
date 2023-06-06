package com.openai.prompt.prompt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        Map<String, String> metadata = new HashMap();

        metadata.put("apiUrl", apiUrl);
        metadata.put("model", model);

        return metadata;
    }

    public List<PromptRecord> getPrompts() {
        return repository.findAll();
    }

    public PromptRecord sendPrompt(String message) throws IOException, InterruptedException {
        List<Message> messages = new ArrayList<>();
        Message newMessage = new Message("user", message);
        messages.add(newMessage);

        Prompt newPrompt = new Prompt(model, messages, 5, 1);
        System.out.println(newPrompt.toString());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
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
