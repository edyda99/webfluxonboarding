package com.example.webfluxonboarding.integration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;


@Document
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Photo {
    @Id
    private String id;

    private String title;

    private String albumId;

    private String url;

    private String thumbnailUrl;

    private String date;

}
