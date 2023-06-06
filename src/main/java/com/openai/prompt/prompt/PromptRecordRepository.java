package com.openai.prompt.prompt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptRecordRepository extends JpaRepository<PromptRecord, Long> {
}
