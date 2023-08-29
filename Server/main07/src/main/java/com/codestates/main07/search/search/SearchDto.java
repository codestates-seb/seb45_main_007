package com.codestates.main07.search.search;

import com.codestates.main07.search.pagination.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class SearchDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String memberId;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String memberId;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private boolean success = true;
        private long searchId;
        private String title;
        private String content;

        // member 구현 후엔 memberId 대신 nickname 사용
        private long memberId;
//        private String nickname;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    public static class MultiResponse<T> {
        private List<T> data;
        private PageInfo pageInfo;

        public MultiResponse(List<T> data, Page page) {
            this.data = data;
            this.pageInfo = new PageInfo(page.getNumber() + 1,
                    page.getSize(), page.getTotalElements(), page.getTotalPages());
        }
    }
}
