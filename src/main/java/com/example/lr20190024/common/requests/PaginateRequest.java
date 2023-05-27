package com.example.lr20190024.common.requests;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginateRequest {
    @Nullable
    Integer page;
    @Nullable
    Integer perPage;

    public Pageable getPageable() {
        return PageRequest.of(
                this.getPage() != null ? this.getPage() : 0,
                this.getPerPage() != null ? this.getPerPage() : 10
        );
    }
}
