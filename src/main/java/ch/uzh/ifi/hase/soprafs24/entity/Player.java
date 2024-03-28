package ch.uzh.ifi.hase.soprafs24.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {

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