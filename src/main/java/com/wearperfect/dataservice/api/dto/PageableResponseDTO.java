package com.wearperfect.dataservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class PageableResponseDTO<T> {
    List<T> list;
    PageMetadata page;

    public static class PageMetadata {
        @JsonProperty
        private long size;
        @JsonProperty
        private long totalElements;
        @JsonProperty
        private long totalPages;
        @JsonProperty
        private long number;

        protected PageMetadata() {
        }

        public PageMetadata(long size, long number, long totalElements, long totalPages) {
            Assert.isTrue(size > -1L, "Size must not be negative!");
            Assert.isTrue(number > -1L, "Number must not be negative!");
            Assert.isTrue(totalElements > -1L, "Total elements must not be negative!");
            Assert.isTrue(totalPages > -1L, "Total pages must not be negative!");
            this.size = size;
            this.number = number;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }

        public PageMetadata(long size, long number, long totalElements) {
            this(size, number, totalElements, size == 0L ? 0L : (long)Math.ceil((double)totalElements / (double)size));
        }

        public long getSize() {
            return this.size;
        }

        public long getTotalElements() {
            return this.totalElements;
        }

        public long getTotalPages() {
            return this.totalPages;
        }

        public long getNumber() {
            return this.number;
        }

        public String toString() {
            return String.format("Metadata { number: %d, total pages: %d, total elements: %d, size: %d }", this.number, this.totalPages, this.totalElements, this.size);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            } else if (obj != null && obj.getClass().equals(this.getClass())) {
                PageMetadata that = (PageMetadata)obj;
                return this.number == that.number && this.size == that.size && this.totalElements == that.totalElements && this.totalPages == that.totalPages;
            } else {
                return false;
            }
        }

        public int hashCode() {
            int result = 17;
            result += 31 * (int)(this.number ^ this.number >>> 32);
            result += 31 * (int)(this.size ^ this.size >>> 32);
            result += 31 * (int)(this.totalElements ^ this.totalElements >>> 32);
            result += 31 * (int)(this.totalPages ^ this.totalPages >>> 32);
            return result;
        }
    }
}

