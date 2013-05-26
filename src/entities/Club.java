package entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:24
 */
@Entity
@Table(name = "CLUBS")
public class Club implements Serializable{
    public Club() {
    }

    public Club(String name) {
        this.name = name;
    }

    public Club(String name, String stadium) {
        this.name = name;
        this.stadium = stadium;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="club_sequence")
    @SequenceGenerator(name="club_sequence", sequenceName="club_sequence")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "STADIUM")
    private String stadium;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club", fetch = FetchType.EAGER)
    private Set<Player> players = new HashSet<Player>();

    public void addPlayer(Player player){
        players.add(player);
        if (player.getClub()!=this) player.setClub(this);
    }

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

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
