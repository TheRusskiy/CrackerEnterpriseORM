package beans;

import entities.Club;
import entities.Match;
import entities.Player;

import javax.ejb.Remote;
import java.sql.Date;
import java.util.Collection;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:17
 */
@Remote
public interface Dao {
    Player getPlayer(Integer id);
    Collection<Player> getPlayers();
    Player createPlayer(String name);
    void save(Player player);
    Match getMatch(Integer id);
    Collection<Match> getMatches();
    Match createMatch(Club home, Club guest);
    void save(Match match);
    Match createMatch(Club home, Club guest, Date date);
    Club getClub(Integer id);
    Collection<Club> getClubs();
    Club createClub(String name);
    void save(Club club);
    String marshal(Object object);

    <T> T unmarshal(String xml, Class c);
}
