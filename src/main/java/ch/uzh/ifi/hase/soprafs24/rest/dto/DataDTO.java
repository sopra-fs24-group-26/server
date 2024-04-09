package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DataDTO {

    @Getter
    @Setter
    SessionDTO session;

    @Getter
    @Setter
    List<PlayerDTO> players;

    @Getter
    @Setter
    List<TileDTO> tiles;
}