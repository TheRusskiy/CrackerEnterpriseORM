package web;

import beans.Dao;
import entities.Club;
import entities.Match;
import entities.Player;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

import static web.JspUtil.formatDate;

/**
 * Author: TheRusskiy
 * Date: 26.05.13
 * Time: 15:11
 */
@WebServlet(name = "web.FootballServlet")
public class FootballServlet extends HttpServlet {
    final private String errorPageURL = "/errors/error_java.jsp";
    @EJB(name = "football_bean")
    Dao dao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loadResources();
            Action action = getAction(request);
            switch (action) {
                case HOME:{ doHome(request, response); break;}
                case ALL_PLAYERS:{ allPlayers(request, response); break;}
                case SHOW_PLAYER:{ showPlayer(request, response); break;}
                case EDIT_PLAYER_GET:{ editPlayerGet(request, response); break;}
                case EDIT_PLAYER_POST:{ editPlayerPost(request, response); break;}
                case EDIT_PLAYER_DELETE:{ editPlayerDelete(request, response); break;}
                case NEW_PLAYER_GET:{ newPlayerGet(request, response); break;}
                case NEW_PLAYER_POST:{ newPlayerPost(request, response); break;}
                case NEW_PLAYER_XML:{ newPlayerXml(request, response); break;}
                case ALL_MATCHES:{ allMatches(request, response); break;}
                case SHOW_MATCH:{ showMatch(request, response); break;}
                case EDIT_MATCH_GET:{ editMatchGet(request, response); break;}
                case EDIT_MATCH_POST:{ editMatchPost(request, response); break;}
                case EDIT_MATCH_DELETE:{ editMatchDelete(request, response); break;}
                case NEW_MATCH_GET:{ newMatchGet(request, response); break;}
                case NEW_MATCH_POST:{ newMatchPost(request, response); break;}
                case NEW_MATCH_XML:{ newMatchXml(request, response); break;}
                case ALL_CLUBS:{ allClubs(request, response); break;}
                case SHOW_CLUB:{ showClub(request, response); break;}
                case EDIT_CLUB_GET:{ editClubGet(request, response); break;}
                case EDIT_CLUB_POST:{ editClubPost(request, response); break;}
                case EDIT_CLUB_DELETE:{ editClubDelete(request, response); break;}
                case NEW_CLUB_GET:{ newClubGet(request, response); break; }
                case NEW_CLUB_POST:{ newClubPost(request, response); break; }
                case NEW_CLUB_XML:{ newClubXml(request, response); break; }
                default:{ throw new RuntimeException("WTF?");}
            }
            if (action.redirect){
                String url = encodeParamsInURL(request, action.responseURI);
                response.sendRedirect(url);
            } else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(action.responseURI);
                dispatcher.forward(request, response);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            sendErrorRedirect(request, response, errorPageURL, e);
        }
    }
    private void doHome(HttpServletRequest request, HttpServletResponse response) {
    }
    private void allPlayers(HttpServletRequest request, HttpServletResponse response) {
        Collection<Player> players = dao.getPlayers();
        request.setAttribute("players", players);
    }
    private void showPlayer(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Player player = dao.getPlayer(id);
        request.setAttribute("player", player);
        String xml = dao.marshal(player);
        request.setAttribute("xml", xml);
    }
    private void editPlayerGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Player player = dao.getPlayer(id);
        request.setAttribute("player", player);
        Collection<Club> clubs = dao.getClubs();
        request.setAttribute("clubs", clubs);
        String xml = dao.marshal(player);
        request.setAttribute("xml", xml);
    }
    private void editPlayerPost(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        Integer club_id = Integer.valueOf(request.getParameter("club"));
        Player player = dao.getPlayer(id);
        Club club = null;
        if (club_id!=0) club = dao.getClub(club_id);
        player.setClub(club);
        player.setName(name);
        player.setPosition(position);
        dao.save(player);
    }

    private void editPlayerDelete(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        dao.deletePlayer(id);
    }
    private void newPlayerGet(HttpServletRequest request, HttpServletResponse response) {
        Collection<Club> clubs = dao.getClubs();
        request.setAttribute("clubs", clubs);
    }
    private void newPlayerPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        Integer club_id = Integer.valueOf(request.getParameter("club"));
        Club club = null;
        if (club_id!=0) club = dao.getClub(club_id);
        Player player = dao.createPlayer(name);
        player.setClub(club);
        player.setPosition(position);
        dao.save(player);
    }
    private void newPlayerXml(HttpServletRequest request, HttpServletResponse response) {
        String xml = request.getParameter("xml");
        dao.unmarshal(xml, Player.class);
    }
    private void allMatches(HttpServletRequest request, HttpServletResponse response) {
        Collection<Match> matches = dao.getMatches();
        request.setAttribute("matches", matches);
    }
    private void showMatch(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Match match = dao.getMatch(id);
        request.setAttribute("match", match);
        String xml = dao.marshal(match);
        request.setAttribute("xml", xml);
    }
    private void editMatchGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Match match = dao.getMatch(id);
        request.setAttribute("match", match);
        Collection<Club> clubs = dao.getClubs();
        request.setAttribute("clubs", clubs);
    }
    private void editMatchPost(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String date_string = request.getParameter("date");
        Integer home_id = Integer.valueOf(request.getParameter("home"));
        Integer guest_id = Integer.valueOf(request.getParameter("guest"));
        Match match = dao.getMatch(id);
        Club home = dao.getClub(home_id);
        Club guest = dao.getClub(guest_id);
        match.setHome(home);
        match.setGuest(guest);
        Date date = formatDate(date_string);
        match.setDate(date);
        dao.save(match);
    }
    private void editMatchDelete(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        dao.deleteMatch(id);
    }
    private void newMatchGet(HttpServletRequest request, HttpServletResponse response) {
        Collection<Club> clubs = dao.getClubs();
        request.setAttribute("clubs", clubs);
    }
    private void newMatchPost(HttpServletRequest request, HttpServletResponse response) {
        String date_string = request.getParameter("date");
        Integer home_id = Integer.valueOf(request.getParameter("home"));
        Integer guest_id = Integer.valueOf(request.getParameter("guest"));
        Club home = dao.getClub(home_id);
        Club guest = dao.getClub(guest_id);
        Date date = formatDate(date_string);
        Match match = dao.createMatch(home, guest, date);
    }
    private void newMatchXml(HttpServletRequest request, HttpServletResponse response) {
        String xml = request.getParameter("xml");
        dao.unmarshal(xml, Match.class);
    }
    private void allClubs(HttpServletRequest request, HttpServletResponse response) {
        Collection<Club> clubs = dao.getClubs();
        request.setAttribute("clubs", clubs);
    }
    private void showClub(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Club club = dao.getClub(id);
        request.setAttribute("club", club);
        String xml = dao.marshal(club);
        request.setAttribute("xml", xml);
    }
    private void editClubGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Club club = dao.getClub(id);
        request.setAttribute("club", club);
    }
    private void editClubPost(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String stadium = request.getParameter("stadium");
        Club club = dao.getClub(id);
        club.setName(name);
        club.setStadium(stadium);
        dao.save(club);
    }
    private void editClubDelete(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        dao.deleteClub(id);
    }
    private void newClubGet(HttpServletRequest request, HttpServletResponse response) {
    }
    private void newClubPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String stadium = request.getParameter("stadium");
        Club club = dao.createClub(name);
        club.setStadium(stadium);
        dao.save(club);
    }
    private void newClubXml(HttpServletRequest request, HttpServletResponse response) {
        String xml = request.getParameter("xml");
        dao.unmarshal(xml, Club.class);
    }


    private Action getAction(HttpServletRequest request) {
        String path = request.getRequestURI().toLowerCase();
        for(Action action:Action.values()){
            if (path.matches(action.requestPattern)) return action;
        }
        throw new RuntimeException("Unknown route: "+request.getServletPath());
    }

    private void loadResources(){
        if (dao!=null) return;
        try {
            InitialContext ic = new InitialContext();
            dao = (Dao)ic.lookup(Dao.class.getName());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public enum Action {
        HOME("/?index/?", "/home/index.jsp"),
        ALL_PLAYERS("/?players/?", "/jsp_players/all_players.jsp"),
        SHOW_PLAYER("/?players/show/?", "/jsp_players/show_player.jsp"),
        EDIT_PLAYER_GET("/?players/edit/?", "/jsp_players/edit_player.jsp"),
        EDIT_PLAYER_POST("/?players/edit-post/?", SHOW_PLAYER),
        EDIT_PLAYER_DELETE("/?players/edit-delete/?", ALL_PLAYERS),
        NEW_PLAYER_GET("/?players/new/?", "/jsp_players/new_player.jsp"),
        NEW_PLAYER_POST("/?players/new-post/?", ALL_PLAYERS),
        NEW_PLAYER_XML("/?players/new-xml/?", ALL_PLAYERS),
        ALL_MATCHES("/?matches/?", "/jsp_matches/all_matches.jsp"),
        SHOW_MATCH("/?matches/show/?", "/jsp_matches/show_match.jsp"),
        EDIT_MATCH_GET("/?matches/edit/?", "/jsp_matches/edit_match.jsp"),
        EDIT_MATCH_POST("/?matches/edit-post/?", SHOW_MATCH),
        EDIT_MATCH_DELETE("/?matches/edit-delete/?", ALL_MATCHES),
        NEW_MATCH_GET("/?matches/new/?", "/jsp_matches/new_match.jsp"),
        NEW_MATCH_POST("/?matches/new-post/?", ALL_MATCHES),
        NEW_MATCH_XML("/?matches/new-xml/?", ALL_MATCHES),
        ALL_CLUBS("/?clubs/?", "/jsp_clubs/all_clubs.jsp"),
        SHOW_CLUB("/?clubs/show/?", "/jsp_clubs/show_club.jsp"),
        EDIT_CLUB_GET("/?clubs/edit/?", "/jsp_clubs/edit_club.jsp"),
        EDIT_CLUB_POST("/?clubs/edit-post/?", SHOW_CLUB),
        EDIT_CLUB_DELETE("/?clubs/edit-delete/?", ALL_CLUBS),
        NEW_CLUB_GET("/?clubs/new/?", "/jsp_clubs/new_club.jsp"),
        NEW_CLUB_POST("/?clubs/new-post/?", ALL_CLUBS),
        NEW_CLUB_XML("/?clubs/new-xml/?", ALL_CLUBS);
        public String requestPattern;
        public String responseURI;
        public boolean redirect = false;
        Action(String requestPattern, String responseURI){
            this.requestPattern = requestPattern;
            this.responseURI = responseURI;
        }
        Action(String requestPattern, Action redirect){
            this.requestPattern = requestPattern;
            this.responseURI = redirect.getRequestURI();
            this.redirect=true;
        }
        public String getRequestURI(){
            return "/"+requestPattern.replaceAll(Pattern.quote("/?"), "");
        }
    }
    protected void sendErrorRedirect(HttpServletRequest request, HttpServletResponse response, String errorPageURL, Throwable e) {
        try {
            request.setAttribute ("javax.servlet.jsp.jspException", e);
            getServletConfig().getServletContext().getRequestDispatcher(errorPageURL).forward(request, response);
        } catch (Exception exceptional_exception) {
            throw new RuntimeException(exceptional_exception);
        }
    }

    private String encodeParamsInURL(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        Enumeration<String> params = request.getParameterNames();
        if (params.hasMoreElements()) url += "?";
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = request.getParameter(name);
            url+= URLEncoder.encode(name, "UTF-8");
            url+="=";
            url+= URLEncoder.encode(value, "UTF-8");
            url+="&";
        }
        return url;
    }
}
