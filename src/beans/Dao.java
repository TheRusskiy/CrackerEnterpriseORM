package beans;

import entities.Club;
import entities.Match;
import entities.Player;

import javax.ejb.Remote;
import java.sql.Date;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 14:17
 */
@Remote
public interface Dao {
    Player getPlayer(Integer id);
    Player createPlayer(String name);
    void save(Player player);
    Match getMatch(Integer id);
    Match createMatch(Club home, Club guest);
    void save(Match match);
    Match createMatch(Club home, Club guest, Date date);
    Club getClub(Integer id);
    Club createClub(String name);
    void save(Club club);
}
