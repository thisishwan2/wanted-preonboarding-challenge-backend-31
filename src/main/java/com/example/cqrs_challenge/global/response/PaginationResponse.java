package com.example.cqrs_challenge.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 페이지네이션 응답 형식
 * @param <T> 응답 데이터
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {

    private List<T> items;

    private Pagination pagination;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination {

        private long totalItems;

        private int totalPages;

        private int currentPage;

        private int perPage;
    }
}
