package com.example.webfluxonboarding.integration.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressResponse {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResponse geo;
}
