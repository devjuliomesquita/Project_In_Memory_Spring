package com.juliomesquita.in_memory_database.domain.utils;

import java.util.List;

public record PageableGeneric(
        List<?> object,
        Integer page,
        Integer perPage,
        Integer totalPages,
        Long total
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<?> object;
        private Integer page;
        private Integer perPage;
        private Integer totalPages;
        private Long total;

        private Builder() {
        }

        public Builder object(List<?> val) {
            object = val;
            return this;
        }

        public Builder page(Integer val) {
            page = val;
            return this;
        }

        public Builder perPage(Integer val) {
            perPage = val;
            return this;
        }

        public Builder totalPages(Integer val) {
            totalPages = val;
            return this;
        }

        public Builder total(Long val) {
            total = val;
            return this;
        }

        public PageableGeneric build() {
            return new PageableGeneric(
                    this.object,
                    this.page,
                    this.perPage,
                    this.totalPages,
                    this.total
            );
        }
    }
}
