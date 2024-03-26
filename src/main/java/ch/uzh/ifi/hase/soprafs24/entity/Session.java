package ch.uzh.ifi.hase.soprafs24.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SESSION")
public class Session implements Serializable {

    @Id
    @Getter
    @Setter
    private String sessionID;

    @Getter
    @Setter
    @Column
    private String turnPlayer;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer playerCount;
}