package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DataDTO {

    @Getter
    @Setter
    PlayerDTO playerDTO;

    @Getter
    @Setter
    SessionDTO sessionDTO;

    @Getter
    @Setter
    List<PlayerDTO> playerDTOs;

    @Getter
    @Setter
    List<TileDTO> tileDTOs;
}