package main;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "User")
@Table(name = "GameUser")
public class User {
    public User() {
        this.userName = "";
        this.gameLevel = 0;
        this.score = 0;
    }

    public User(String userName, Integer gameLevel) {
        this.userName = userName;
        this.gameLevel = gameLevel;
        this.score = 0;
    }

    public User(String userName, Integer gameLevel, Integer score) {
        this.userName = userName;
        this.gameLevel = gameLevel;
        this.score = score;
    }

    @Id
    @SequenceGenerator(
            name = "userSequence",
            sequenceName = "userSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "userSequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    @javax.persistence.Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(
            name = "userName",
            nullable = false

    )
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Column(
            name = "gameLevel",
            nullable = false
    )
    private Integer gameLevel;

    public void setGameLevel(Integer gameLevel) {
        this.gameLevel = gameLevel;
    }

    public Integer getGameLevel() {
        return gameLevel;
    }

    @Column(
            name = "gameScore",
            nullable = true
    )
    private Integer score;

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

}
