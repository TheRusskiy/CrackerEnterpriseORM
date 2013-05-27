package web;

import beans.Dao;

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
                case EDIT_PLAYER:{
                    editPlayer(request, response);
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
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(action.responseURI);
            dispatcher.forward(request, response);
//        Player player = dao.createPlayer("DIMA");
//        Club club = dao.createClub("MU");
//        club.addPlayer(player);
//        dao.save(club);
//        player = dao.getPlayer(player.getId());
//        ServletOutputStream out = response.getOutputStream();
//        out.print(player.getId()+":"+player.getName()+", club: "+player.getClub().getName());
        } catch (Throwable e) {
            sendErrorRedirect(request, response, errorPageURL, e);
        }
    }

    private void allPlayers(HttpServletRequest request, HttpServletResponse response) {
        dao.getPlayers();
    }
    private void showPlayer(HttpServletRequest request, HttpServletResponse response) {

    }
    private void editPlayer(HttpServletRequest request, HttpServletResponse response) {

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
            if (path.matches(action.requestURI)) return action;
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
    enum Action {
        ALL_PLAYERS("/?players/?$", "/index.jsp"),
        SHOW_PLAYER("/?players/show/?$", ""),
        EDIT_PLAYER("/?players/edit/?$", ""),
        NEW_PLAYER("/?players/new/?$", ""),
        ALL_MATCHES("/?matches/?$", ""),
        SHOW_MATCH("/?matches/show/?$", ""),
        EDIT_MATCH("/?matches/edit/?$", ""),
        NEW_MATCH("/?matches/new/?$", ""),
        ALL_CLUBS("/?clubs/?$", ""),
        SHOW_CLUB("/?clubs/show/?$", ""),
        EDIT_CLUB("/?clubs/edit/?$", ""),
        NEW_CLUB("/?clubs/new/?$", "");
        final public String requestURI;
        final public String responseURI;
        Action(String requestURI, String responseURI){
            this.requestURI = requestURI;
            this.responseURI = responseURI;
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
}
