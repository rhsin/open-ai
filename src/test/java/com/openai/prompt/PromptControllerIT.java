package com.openai.prompt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openai.prompt.exception.ApiError;
import com.openai.prompt.prompt.models.PromptDTO;
import com.openai.prompt.prompt.models.PromptRecord;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PromptControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getPromptDTOs() {
        ParameterizedTypeReference<List<PromptDTO>> prompts = new ParameterizedTypeReference<List<PromptDTO>>() {};

        ResponseEntity<List<PromptDTO>> response = template.exchange("/prompt", HttpMethod.GET, null, prompts);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(2);
        assertThat(response.getBody().get(0).getModel()).isEqualTo("gpt-3.5-turbo-0613");
        assertThat(response.getBody().get(0).getPrompts().size()).isGreaterThanOrEqualTo(1);
        assertThat(response.getBody().get(1).getResponses().size()).isGreaterThanOrEqualTo(1);
        assertThat(response.getBody().get(1).getTotal_tokens()).isGreaterThanOrEqualTo(2);
    }

    @Test
    public void getPromptRecords() {
        ParameterizedTypeReference<List<PromptRecord>> prompts = new ParameterizedTypeReference<List<PromptRecord>>() {};

        ResponseEntity<List<PromptRecord>> response = template.exchange("/prompt/records", HttpMethod.GET, null, prompts);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(2);
        assertThat(response.getBody().get(0).getModel()).isEqualTo("gpt-3.5-turbo-0613");
        assertThat(response.getBody().get(1).getModel()).isEqualTo("gpt-3.5-turbo-0613");
    }

    @Test
    public void getPromptRecord() {
        ResponseEntity<PromptRecord> response = template.getForEntity("/prompt/records/2", PromptRecord.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getModel()).isEqualTo("gpt-3.5-turbo-0613");
        assertThat(response.getBody().getMax_tokens()).isEqualTo(5);
    }

    @Test
    public void findPromptDTOs() {
        ParameterizedTypeReference<List<PromptDTO>> prompts = new ParameterizedTypeReference<List<PromptDTO>>() {};

        ResponseEntity<List<PromptDTO>> response = template.exchange("/prompt/find/response?keyword=hello", 
            HttpMethod.GET, null, prompts);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(1);
        assertThat(response.getBody().get(0).getModel()).isEqualTo("gpt-3.5-turbo-0613");
        assertThat(response.getBody().get(0).getPrompts().size()).isGreaterThanOrEqualTo(1);
        assertThat(response.getBody().get(0).getResponses().size()).isGreaterThanOrEqualTo(1);
        assertThat(response.getBody().get(0).getTotal_tokens()).isGreaterThanOrEqualTo(5);
    }

    @Test
    public void getUsage() {
        ParameterizedTypeReference<Map<String, String>> usage = new ParameterizedTypeReference<Map<String, String>>() {};

        ResponseEntity<Map<String, String>> response = template.exchange("/usage?month=6", HttpMethod.GET, null, usage);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().containsKey("total_requests")).isTrue();
        assertThat(response.getBody().containsKey("total_usage")).isTrue();
        assertThat(response.getBody().containsKey("total_cost")).isTrue();
    }

    @Test
    public void getPromptRecordError() {
        ParameterizedTypeReference<ApiError> error = new ParameterizedTypeReference<ApiError>() {};

        ResponseEntity<ApiError> response = template.exchange("/prompt/records/99999", HttpMethod.GET, null, error);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getStatus()).isEqualTo(404);
        assertThat(response.getBody().getMessage()).contains("Prompt record not found: 99999");
    }
}

