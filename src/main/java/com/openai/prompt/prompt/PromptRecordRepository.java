package com.openai.prompt.prompt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openai.prompt.prompt.models.PromptRecord;

public interface PromptRecordRepository extends JpaRepository<PromptRecord, Long> {
}
