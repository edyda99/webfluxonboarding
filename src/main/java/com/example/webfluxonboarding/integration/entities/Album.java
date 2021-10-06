package com.example.webfluxonboarding.integration.entities;

import com.example.webfluxonboarding.core.dto.response.AlbumResponseDto;
import com.example.webfluxonboarding.core.dto.response.PhotoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;

import java.sql.Date;
import java.util.*;


@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Album {
    @Id
    private String id;

    private String title;

    private String date;

    private String userId;

    @DBRef
    private Collection<Photo> photos = Collections.synchronizedSet(new HashSet<>());

    public Album addPhoto(Photo photo){
        if(photo==null) return this;
        else this.photos.add(photo);
        return this;
    }

}
