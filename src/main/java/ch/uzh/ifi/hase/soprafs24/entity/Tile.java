package ch.uzh.ifi.hase.soprafs24.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TILE")
public class Tile implements Serializable {

    @Id
    @Getter
    @Setter
    // UUID 4 string
    private String id;

    @Getter
    @Setter
    @Column
    // UUID 4 string
    private String sessionId;

    @Getter
    @Setter
    @Column
    private Integer rotation;

    @Getter
    @Setter
    @Column
    private Integer coordinateX;

    @Getter
    @Setter
    @Column
    private Integer coordinateY;
}