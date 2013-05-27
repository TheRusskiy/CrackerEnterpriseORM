package entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:24
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "PLAYERS")
public class Player  implements Serializable {
    public Player() {}
    public Player(String name) {this.name = name;}
    public Player(String name, Club club) { this.name = name; this.club = club;}
    public Player(String name, String position, Club club) { this.name = name;
                                   this.position = position; this.club = club; }

    @XmlElement
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="player_sequence")
    @SequenceGenerator(name="player_sequence", sequenceName="player_sequence")
    @Column(name = "ID")
    private Integer id;

    @XmlElement
    @NotNull
    @Column(name = "NAME")
    private String name;

    @XmlElement
    @Column(name = "POSITION")
    private String position;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID", nullable = true)
    private Club club;

    @XmlElement
    @Transient
    private Integer clubID;

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
        if (club!=null){
            clubID=club.getId();
            club.addPlayer(this);
        }
    }

    @Deprecated
    /**
     * @deprecated - for marshalling
     */
    public Integer getClubID(){
        return clubID;
    }
}
