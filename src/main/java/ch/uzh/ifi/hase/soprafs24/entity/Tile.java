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
    private String tileID;

    @Getter
    @Setter
    @Column
    // UUID 4 string
    private String sessionID;

    @Getter
    @Setter
    @Column
    // What type is which can be seen seen in TileType file
    private Integer type;

    @Getter
    @Setter
    @Column
    // if isPlaced is false, means the tile is in a players hand
    private Boolean isPlaced;

    @Getter
    @Setter
    @Column(nullable = true)
    private Integer rotation;

    @Getter
    @Setter
    @Column(nullable = true)
    private Integer coordinateX;

    @Getter
    @Setter
    @Column(nullable = true)
    private Integer coordinateY;
}