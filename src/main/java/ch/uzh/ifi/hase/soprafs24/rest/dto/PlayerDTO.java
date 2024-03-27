package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

public class PlayerDTO {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private String sessionId;

    @Getter
    @Setter
    @Column
    private Integer role;

    @Getter
    @Setter
    @Column
    private Integer orderIndex;
}