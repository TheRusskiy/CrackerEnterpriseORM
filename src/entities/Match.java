package entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:24
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "MATCHES")
public class Match implements Serializable {
    public Match(){}
    public Match(Club home, Club guest, Date date) { this.date = date;
        setHome(home); setGuest(guest);}
    public Match(Club home, Club guest) { java.util.Date today = new java.util.Date();
        date = new Date(today.getTime()); setHome(home); setGuest(guest);}

    @XmlElement
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="match_sequence")
    @SequenceGenerator(name="match_sequence", sequenceName="match_sequence")
    @Column(name = "ID")
    private Integer id;

    @XmlElement
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "MATCH_DATE")
    private Date date;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "HOME_ID")
    private Club home;

    @Transient
    @XmlElement
    private Integer homeID;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "GUEST_ID")
    private Club guest;

    @Transient
    @XmlElement
    private Integer guestID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Club getHome() {
        return home;
    }

    public void setHome(Club home) {
        this.homeID=home.getId();
        this.home = home;
    }

    public Club getGuest() {
        return guest;
    }

    public void setGuest(Club guest) {
        guestID=guest.getId();
        this.guest = guest;
    }

    @Deprecated
    /**
     * @deprecated - for marshalling
     */
    public Integer getHomeID() {
        return homeID;
    }

    @Deprecated
    /**
     * @deprecated - for marshalling
     */
    public Integer getGuestID() {
        return guestID;
    }
}
