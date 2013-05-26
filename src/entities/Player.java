package entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:24
 */
@Entity
@Table(name = "PLAYERS")
public class Player  implements Serializable {
    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Club club) {
        this.name = name;
        this.club = club;
    }

    public Player(String name, String position, Club club) {
        this.name = name;
        this.position = position;
        this.club = club;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="player_sequence")
    @SequenceGenerator(name="player_sequence", sequenceName="player_sequence")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "POSITION")
    private String position;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID", nullable = true)
    private Club club;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
