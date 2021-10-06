package com.example.webfluxonboarding.integration.entities;

import com.example.webfluxonboarding.core.dto.response.AlbumResponseDto;
import com.example.webfluxonboarding.core.dto.response.UserResponseDto;
import com.example.webfluxonboarding.integration.entities.embedded.Address;
import com.example.webfluxonboarding.integration.entities.embedded.Company;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;

import java.util.*;


@Document
@Getter
@Setter
@Accessors(chain = true)
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    @DBRef
    private Collection<Album> albums = Collections.synchronizedSet(new HashSet<>());

    public User addAlbum(Album album){
        if(album==null) return this;
        else this.albums.add(album);
        return this;
    }
}
