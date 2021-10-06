package com.example.webfluxonboarding.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhotoRequestDto {
    private String id;
    @JsonProperty("album_id")
    private String albumId;
    private String title;
    private String url;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
}
