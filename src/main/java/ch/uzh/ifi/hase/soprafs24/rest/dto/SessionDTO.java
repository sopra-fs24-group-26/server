package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class SessionDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String turnPlayer;

    @Getter
    @Setter
    private Integer playerCount;
}