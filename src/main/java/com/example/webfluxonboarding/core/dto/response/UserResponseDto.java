package com.example.webfluxonboarding.core.dto.response;

import com.example.webfluxonboarding.core.dto.response.AddressResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class UserResponseDto {
    private String id;
    private String name;
    private String username;
    private String email;
    private AddressResponseDto address;
    private String phone;
    private String website;
    private CompanyResponseDto company;
    private Collection<AlbumResponseDto> albums = Collections.synchronizedSet(new HashSet<>());

    public UserResponseDto addAlbum(AlbumResponseDto album){
        if(album==null) return this;
        else this.albums.add(album);
        return this;
    }

}
