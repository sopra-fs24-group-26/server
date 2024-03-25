package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class SessionCreatedDTO {

    @Getter
    @Setter
    private String SessionID;

    @Getter
    @Setter
    private String PlayerID;
}