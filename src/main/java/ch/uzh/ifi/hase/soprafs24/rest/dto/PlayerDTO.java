package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class PlayerDTO {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String sessionId;

    @Getter
    @Setter
    private Integer orderIndex;
}