package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class TileDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String sessionId;

    @Getter
    @Setter
    private Integer rotation;

    @Getter
    @Setter
    private Integer coordinateX;

    @Getter
    @Setter
    private Integer coordinateY;

    @Getter
    @Setter
    private Boolean discarded;
}