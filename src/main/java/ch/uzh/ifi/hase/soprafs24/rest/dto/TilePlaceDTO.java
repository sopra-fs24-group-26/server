package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class TilePlaceDTO {

    @Getter
    @Setter
    // UUID 4 string
    private String tileID;

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
