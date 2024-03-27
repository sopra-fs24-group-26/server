package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class TileIDTypeDTO {

    @Getter
    @Setter
    // What type is which can be seen seen in TileType file
    private Integer type;

    @Getter
    @Setter
    private String sessionID;
}