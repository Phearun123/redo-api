package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.redoapi.common.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserMainResponse {

    List<UserResponse> data;
    Pagination pagination;

    @Builder
    public UserMainResponse(List<UserResponse> data, Page<?> page) {
        this.data = data;
        this.pagination = new Pagination(page);
    }



}
