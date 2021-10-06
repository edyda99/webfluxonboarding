package com.example.webfluxonboarding.integration.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    private AddressResponse address;
    private String phone;
    private String website;
    private CompanyResponse company;
    private Collection<AlbumResponse> albums = Collections.synchronizedSet(new HashSet<>());
}
