package com.example.webfluxonboarding.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AlbumRequestDto {
    private String id;
    @JsonProperty("user_id")
    private String userId;
    private String title;
}
