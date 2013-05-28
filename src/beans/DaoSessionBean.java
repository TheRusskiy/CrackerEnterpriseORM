package beans;

import entities.Club;
import entities.Match;
import entities.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    public Collection<Player> getPlayers() {
        Query query = em.createQuery("select p from Player p");
        List list = query.getResultList();
        return list;
    }


    @Override
    public Player createPlayer(String name) {
        Player player = new Player(name);
        em.persist(player);
        em.flush();
        return player;
    }

    @Override
    public void deletePlayer(Integer id) {
        em.remove(getPlayer(id));
    }

    @Override
    public void deleteMatch(Integer id) {
        em.remove(getMatch(id));
    }

    @Override
    public void deleteClub(Integer id) {
        em.remove(getClub(id));
    }

    @Override
    public Match getMatch(Integer id) {
        return em.find(Match.class, id);
    }

    @Override
    public Collection<Match> getMatches() {
        Query query = em.createQuery("select p from Match p");
        List list = query.getResultList();
        return list;
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
    public Collection<Club> getClubs() {
        Query query = em.createQuery("select p from Club p");
        List list = query.getResultList();
        return list;
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
        em.merge(match);
        em.flush();
    }

    @Override
    public void save(Club club) {
        em.merge(club);
        em.flush();
    }

    @Override
    public String marshal(Object object){
        try {
            storeEntity(object);
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(object, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    public void marshal(Object object, OutputStream out){
        try {
            storeEntity(object);
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(object, out);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T unmarshal(String xml, Class c){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T obj = (T) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            restoreEntity(obj);
            return obj;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T unmarshal(InputStream in, Class c){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T obj = (T) jaxbUnmarshaller.unmarshal(in);
            restoreEntity(obj);
            return obj;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private void restoreEntity(Object obj) {
        if (obj.getClass()==Player.class){
            Player p = (Player) obj;
            p.setClub(getClub(p.getClubID()));
        } else if (obj.getClass()==Match.class){
            Match p = (Match) obj;
            p.setGuest(getClub(p.getGuestID()));
            p.setHome(getClub(p.getHomeID()));
        }  else if (obj.getClass()==Club.class){
            Club p = (Club) obj;
            for(Integer id: p.getPlayerIDs()){
                p.addPlayer(getPlayer(id));
            }
        } else {
            return;
        }
        em.persist(obj);
        em.flush();
    }

    private void storeEntity(Object obj) {
        if (obj.getClass()==Player.class){
            Player p = (Player) obj;
            p.setClub(p.getClub());
        } else if (obj.getClass()==Match.class){
            Match p = (Match) obj;
            p.setGuest(p.getGuest());
            p.setHome(p.getHome());
        }  else if (obj.getClass()==Club.class){
            Club c = (Club) obj;
            //array to avoid concurrent modification exception:
            Player[] players = c.getPlayers().toArray(new Player[]{});
            for (Player player : players) {
                c.addPlayer(player);
            }
        } else {
            return;
        }
        em.merge(obj);
        em.flush();
    }


}
