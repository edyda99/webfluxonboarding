package com.example.webfluxonboarding.integration.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhotoResponse {
    private String id;
    private String albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
