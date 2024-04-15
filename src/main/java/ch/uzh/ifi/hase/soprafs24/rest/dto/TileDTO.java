package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class TileDTO {

    @Getter
    @Setter
    // UUID 4 string
    private String id;

    @Getter
    @Setter
    // UUID 4 string
    private String sessionId;

    @Getter
    @Setter
    // What type is which can be seen seen in TileType file
    private Integer type;

    @Getter
    @Setter
    private Integer rotation;

    @Getter
    @Setter
    private Integer coordinateX;

    @Getter
    @Setter
    private Integer coordinateY;
}