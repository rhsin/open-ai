package com.openai.prompt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PromptControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getPrompts() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/prompt")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
			.andExpect(jsonPath("$.[0].model", is("gpt-3.5-turbo-0301")))
			.andExpect(jsonPath("$.[1].model", is("gpt-3.5-turbo-0301")));
	}

	@Test
	public void getUsage() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/usage?month=6")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.total_requests", notNullValue()))
			.andExpect(jsonPath("$.total_usage", notNullValue()));
	}
}
