package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class JoinDTO {

    @Getter
    @Setter
    private String playerName;

    @Getter
    @Setter
    private String sessionId;
}