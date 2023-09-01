package com.codestates.main07.memoryBoard.answer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemoryBoardAnswerMultiResponseDto<T> {
    private boolean success;
    private List<T> memoryBoardAnswers;

    public MemoryBoardAnswerMultiResponseDto(List<T> memoryBoardAnswers, boolean success) {
        this.memoryBoardAnswers = memoryBoardAnswers;
        this.success = success;
    }
}
