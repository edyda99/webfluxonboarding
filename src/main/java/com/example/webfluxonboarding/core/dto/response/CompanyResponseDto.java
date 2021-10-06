package com.example.webfluxonboarding.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class CompanyResponseDto {
    private String name;
    private String catchPhrase;
    private String bs;
}
