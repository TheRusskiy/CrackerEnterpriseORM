package web;

import beans.Dao;
import entities.Club;
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
import java.util.Enumeration;
import java.util.regex.Pattern;

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
                case ALL_PLAYERS:{
                    allPlayers(request, response);
                    break;
                }
                case SHOW_PLAYER:{
                    showPlayer(request, response);
                    break;
                }
                case EDIT_PLAYER_GET:{
                    editPlayerGet(request, response);
                    break;
                }
                case EDIT_PLAYER_POST:{
                    editPlayerPost(request, response);
                    break;
                }
                case EDIT_PLAYER_DELETE:{
                    editPlayerDelete(request, response);
                    break;
                }
                case NEW_PLAYER:{
                    newPlayer(request, response);
                    break;
                }
                case ALL_MATCHES:{
                    allMatches(request, response);
                    break;
                }
                case SHOW_MATCH:{
                    showMatch(request, response);
                    break;
                }
                case EDIT_MATCH:{
                    editMatch(request, response);
                    break;
                }
                case NEW_MATCH:{
                    newMatch(request, response);
                    break;
                }
                case ALL_CLUBS:{
                    allClubs(request, response);
                    break;
                }
                case SHOW_CLUB:{
                    showClub(request, response);
                    break;
                }
                case EDIT_CLUB:{
                    editClub(request, response);
                    break;
                }
                case NEW_CLUB:{
                    newClub(request, response);
                    break;
                }
                default:{
                    throw new RuntimeException("WTF?");
                }
            }
            if (action.redirect){
                String url = encodeParamsInURL(request, action.responseURI);
                response.sendRedirect(url);
            } else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(action.responseURI);
                dispatcher.forward(request, response);
            }
        } catch (Throwable e) {

            sendErrorRedirect(request, response, errorPageURL, e);
        }
    }

    private void allPlayers(HttpServletRequest request, HttpServletResponse response) {
        Collection<Player> players = dao.getPlayers();
        request.setAttribute("players", players);
    }
    private void showPlayer(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Player player = dao.getPlayer(id);
        request.setAttribute("player", player);
    }
    private void editPlayerGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Player player = dao.getPlayer(id);
        request.setAttribute("player", player);
        Collection<Club> clubs = dao.getClubs();
        request.setAttribute("clubs", clubs);
    }
    private void editPlayerPost(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        Integer club_id = Integer.valueOf(request.getParameter("club"));
        Player player = dao.getPlayer(id);
        Club club = dao.getClub(club_id);
        player.setClub(club);
        player.setName(name);
        player.setPosition(position);
        dao.save(player);
    }
    private void editPlayerDelete(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        dao.deletePlayer(id);
    }
    private void newPlayer(HttpServletRequest request, HttpServletResponse response) {

    }
    private void allMatches(HttpServletRequest request, HttpServletResponse response) {

    }
    private void showMatch(HttpServletRequest request, HttpServletResponse response) {

    }
    private void editMatch(HttpServletRequest request, HttpServletResponse response) {

    }
    private void newMatch(HttpServletRequest request, HttpServletResponse response) {

    }
    private void allClubs(HttpServletRequest request, HttpServletResponse response) {

    }
    private void showClub(HttpServletRequest request, HttpServletResponse response) {

    }
    private void editClub(HttpServletRequest request, HttpServletResponse response) {

    }
    private void newClub(HttpServletRequest request, HttpServletResponse response) {

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
        ALL_PLAYERS("/?players/?", "/jsp_players/all_players.jsp"),
        SHOW_PLAYER("/?players/show/?", "/jsp_players/show_player.jsp"),
        EDIT_PLAYER_GET("/?players/edit/?", "/jsp_players/edit_player.jsp"),
        EDIT_PLAYER_POST("/?players/edit-post/?", SHOW_PLAYER),
        EDIT_PLAYER_DELETE("/?players/edit-delete/?", ALL_PLAYERS),
        NEW_PLAYER("/?players/new/?", ""),
        ALL_MATCHES("/?matches/?", ""),
        SHOW_MATCH("/?matches/show/?", ""),
        EDIT_MATCH("/?matches/edit/?", ""),
        NEW_MATCH("/?matches/new/?", ""),
        ALL_CLUBS("/?clubs/?", ""),
        SHOW_CLUB("/?clubs/show/?", ""),
        EDIT_CLUB("/?clubs/edit/?", ""),
        NEW_CLUB("/?clubs/new/?", "");
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
            String result = "/"+requestPattern.replaceAll(Pattern.quote("/?"), "");
            return result;
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
