package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class SessionDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Integer turnPlayer;

    @Getter
    @Setter
    private String seed;
}