package com.example.webfluxonboarding.core.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class PhotoResponseDto {
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
    @JsonIgnore
    private String Date;
}
