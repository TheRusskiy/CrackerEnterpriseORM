package entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:24
 */
@Entity
@Table(name = "MATCHES")
public class Match implements Serializable {
    public Match(){
    }

    public Match(Club home, Club guest, Date date) {
        this.date = date;
        this.home = home;
        this.guest = guest;
    }

    public Match(Club home, Club guest) {
        java.util.Date today = new java.util.Date();
        date = new Date(today.getTime());
        this.home = home;
        this.guest = guest;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="match_sequence")
    @SequenceGenerator(name="match_sequence", sequenceName="match_sequence")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "MATCH_DATE")
    private Date date;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "HOME_ID")
    private Club home;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "GUEST_ID")
    private Club guest;

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
        this.home = home;
    }

    public Club getGuest() {
        return guest;
    }

    public void setGuest(Club guest) {
        this.guest = guest;
    }
}
