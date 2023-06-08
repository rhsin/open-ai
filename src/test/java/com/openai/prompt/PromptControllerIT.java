package com.openai.prompt;

import com.openai.prompt.prompt.PromptRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PromptControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getPrompts() {
        ParameterizedTypeReference<List<PromptRecord>> prompts = new ParameterizedTypeReference<List<PromptRecord>>() {};

        ResponseEntity<List<PromptRecord>> response = template.exchange("/prompt", HttpMethod.GET, null, prompts);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(2);
        assertThat(response.getBody().get(0).getModel()).isEqualTo("gpt-3.5-turbo-0301");
        assertThat(response.getBody().get(1).getModel()).isEqualTo("gpt-3.5-turbo-0301");
    }

    @Test
    public void getUsage() {
        ParameterizedTypeReference<Map<String, String>> usage = new ParameterizedTypeReference<Map<String, String>>() {};

        ResponseEntity<Map<String, String>> response = template.exchange("/usage?month=6", HttpMethod.GET, null, usage);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().containsKey("total_requests")).isTrue();
        assertThat(response.getBody().containsKey("total_usage")).isTrue();
    }
}

