package com.example.webfluxonboarding.integration.model.response;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private String id;
    private String userId;
    private String title;
    private Collection<PhotoResponse> photos = Collections.synchronizedSet(new HashSet<>());
}
