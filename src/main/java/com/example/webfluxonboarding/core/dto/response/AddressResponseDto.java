package com.example.webfluxonboarding.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class AddressResponseDto {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResponseDto geo;
}
