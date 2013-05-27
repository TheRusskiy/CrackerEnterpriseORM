package entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:24
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "CLUBS")
public class Club implements Serializable{
    public Club() {}
    public Club(String name) { this.name = name;}
    public Club(String name, String stadium) {this.name = name; this.stadium = stadium;}

    @XmlElement
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="club_sequence")
    @SequenceGenerator(name="club_sequence", sequenceName="club_sequence")
    @Column(name = "ID")
    private Integer id;

    @XmlElement
    @NotNull
    @Column(name = "NAME")
    private String name;

    @XmlElement
    @Column(name = "STADIUM")
    private String stadium;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club", fetch = FetchType.EAGER)
    private Set<Player> players = new HashSet<Player>();

    @XmlElement
    @Transient
    private Set<Integer> playerIDs = new HashSet<Integer>();

    public void addPlayer(Player player){
        if (players==null) players = new HashSet<Player>(); // just...
        if (playerIDs==null) playerIDs = new HashSet<Integer>(); //...in case
        players.add(player);
        playerIDs.add(player.getId());
        if (player.getClub()!=this) player.setClub(this);
    }

    public void removePlayer(Player player){
        players.remove(player);
        playerIDs.remove(player.getId());
        if (player.getClub()==this) player.setClub(null);
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

    @Deprecated
    /**
     * @deprecated - for marshalling
     */
    public Set<Integer> getPlayerIDs() {
        return playerIDs;
    }
}
