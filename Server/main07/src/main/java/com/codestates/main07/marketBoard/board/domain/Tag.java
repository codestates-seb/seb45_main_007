package com.codestates.main07.marketBoard.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tag {
    SALE("TAG_SALE", "판매중"),
    COMPLETED("TAG_COMPLETED", "판매 완료");

    private final String key;
    private final String title;
}
