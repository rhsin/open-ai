package com.openai.prompt.prompt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class PromptService {

    @Value("${OPENAI_URL}")
    private String apiUrl;

    @Value("${OPENAI_MODEL}")
    private String model;

    @Value("${OPENAI_KEY}")
    private String apiKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public Map<String, String> getMetadata() {
        Map<String, String> metadata = new HashMap();

        metadata.put("apiUrl", apiUrl);
        metadata.put("model", model);

        return metadata;
    }

    public PromptResponse sendPrompt(String prompt) throws IOException, InterruptedException {
        Prompt newPrompt = new Prompt(model, prompt);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(newPrompt)))
            .build();

        PromptResponse response = (PromptResponse) httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);

        return response;
    }
}
