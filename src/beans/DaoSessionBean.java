package beans;

import entities.Club;
import entities.Match;
import entities.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:03
 */
@Stateless(name = "football_bean")
public class DaoSessionBean implements Dao{
    @PersistenceContext(unitName = "footballUnit")
    private EntityManager em;

    @Override
    public Player getPlayer(Integer id) {
        return em.find(Player.class, id);
    }

    @Override
    public Player createPlayer(String name) {
        Player player = new Player(name);
        em.persist(player);
        em.flush();
        return player;
    }

    @Override
    public Match getMatch(Integer id) {
        return em.find(Match.class, id);
    }

    @Override
    public Match createMatch(Club home, Club guest) {
        Match match = new Match(home, guest);
        em.persist(match);
        em.flush();
        return match;
    }

    @Override
    public Match createMatch(Club home, Club guest, Date date) {
        Match match = new Match(home, guest, date);
        em.persist(match);
        em.flush();
        return match;
    }

    @Override
    public Club getClub(Integer id) {
        return em.find(Club.class, id);
    }

    @Override
    public Club createClub(String name) {
        Club club = new Club(name);
        em.persist(club);
        em.flush();
        return club;
    }

    @Override
    public void save(Player player) {
        em.merge(player);
        em.flush();
    }

    @Override
    public void save(Match match) {
        em.persist(match);
    }

    @Override
    public void save(Club club) {
        em.merge(club);
    }


}
