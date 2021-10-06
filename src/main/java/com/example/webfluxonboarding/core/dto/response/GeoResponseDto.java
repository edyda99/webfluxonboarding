package com.example.webfluxonboarding.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class GeoResponseDto {
    private String lat;
    private String lng;
}
