package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class TileDTO {

    @Getter
    @Setter
    // UUID 4 string
    private String tileID;

    @Getter
    @Setter
    // UUID 4 string
    private String sessionID;

    @Getter
    @Setter
    // What type is which can be seen seen in TileType file
    private Integer type;

    // if isPlaced is false, means the tile is in a players hand
    @Getter
    @Setter
    private Boolean isPlaced;

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
