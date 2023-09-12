package com.codestates.main07.marketBoard.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessDto {
    private boolean success;

    public SuccessDto(boolean success) {
        this.success = success;
    }
}
