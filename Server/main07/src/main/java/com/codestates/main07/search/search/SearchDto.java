package com.codestates.main07.search.search;

import com.codestates.main07.search.pagination.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
        private String category;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String memberId;
        private String title;
        private String content;
        private String category;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private boolean success;
        private long searchId;
        private String title;
        private String content;
        private String category;
        private boolean adopted;

        // member 구현 후엔 memberId 대신 nickname 사용
        private long memberId;
//        private String nickname;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    // Response -> success 없는 버전
    public static class Responses {
        private long searchId;
        private String title;
        private String content;
        private String category;
        private boolean adopted;

        // member 구현 후엔 memberId 대신 nickname 사용
        private long memberId;
//        private String nickname;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    public static class MultiResponse<T> {
        private boolean success;
        private List<T> searches;
        private PageInfo pageInfo;

        public MultiResponse(List<T> searches, Page page, boolean success) {
            this.searches = searches;
            this.pageInfo = new PageInfo(page.getNumber() + 1,
                    page.getSize(), page.getTotalElements(), page.getTotalPages());
            this.success = success;
        }
    }

    @Getter
    public static class SuccessResponse {
        private boolean success;

        public SuccessResponse(boolean success) {
            this.success = success;
        }
    }
}
