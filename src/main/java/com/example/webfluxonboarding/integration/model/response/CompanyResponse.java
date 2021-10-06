package com.example.webfluxonboarding.integration.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CompanyResponse {
    private String name;
    private String catchPhrase;
    private String bs;
}
