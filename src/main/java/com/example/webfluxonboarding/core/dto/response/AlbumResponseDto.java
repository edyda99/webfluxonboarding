package com.example.webfluxonboarding.core.dto.response;

import com.example.webfluxonboarding.core.dto.response.PhotoResponseDto;
import com.example.webfluxonboarding.integration.model.response.PhotoResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AlbumResponseDto {
    private String id;
    private String title;
    @JsonIgnore
    private String Date;
    private Collection<PhotoResponseDto> photos = Collections.synchronizedSet(new HashSet<>());

    public AlbumResponseDto addPhoto(PhotoResponseDto photo){
     if(photo==null) return this;
     else this.photos.add(photo);
     return this;
    }
}
