package web;

import beans.Dao;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
    @EJB(name = "football_bean")
    Dao dao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadResources();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadResources();
        Action action = getAction(request);
        switch (action) {
            case ALL_PLAYERS:{
                break;
            }
            case SHOW_PLAYER:{
                break;
            }
            case EDIT_PLAYER:{
                break;
            }
            case NEW_PLAYER:{
                break;
            }
            case ALL_MATCHES:{
                break;
            }
            case SHOW_MATCH:{
                break;
            }
            case EDIT_MATCH:{
                break;
            }
            case NEW_MATCH:{
                break;
            }
            case ALL_CLUBS:{
                break;
            }
            case SHOW_CLUB:{
                break;
            }
            case EDIT_CLUB:{
                break;
            }
            case NEW_CLUB:{
                break;
            }
            default:{
                throw new RuntimeException("WTF?");
            }
        }
//        Player player = dao.createPlayer("DIMA");
//        Club club = dao.createClub("MU");
//        club.addPlayer(player);
//        dao.save(club);
//        player = dao.getPlayer(player.getId());
//        ServletOutputStream out = response.getOutputStream();
//        out.print(player.getId()+":"+player.getName()+", club: "+player.getClub().getName());
    }

    private Action getAction(HttpServletRequest request) {
        Action action = null;
        String path = request.getRequestURI().toLowerCase();
        if (path.matches("/?players/?$")){
            action = Action.ALL_PLAYERS;
        }
        else if (path.matches("/?players/new/?$")){
            action = Action.NEW_PLAYER;
        }
        else if (path.matches("/?players/show/?$")){
            action = Action.SHOW_PLAYER;
        }
        else if (path.matches("/?players/edit/?$")){
            action = Action.EDIT_PLAYER;
        }
        else if (path.matches("/?matches/?$")){
            action = Action.ALL_MATCHES;
        }
        else if (path.matches("/?matches/new/?$")){
            action = Action.NEW_MATCH;
        }
        else if (path.matches("/?matches/show/?$")){
            action = Action.SHOW_MATCH;
        }
        else if (path.matches("/?matches/edit/?$")){
            action = Action.EDIT_MATCH;
        }
        else if (path.matches("/?clubs/?$")){
            action = Action.ALL_CLUBS;
        }
        else if (path.matches("/?clubs/new/?$")){
            action = Action.NEW_CLUB;
        }
        else if (path.matches("/?clubs/show/?$")){
            action = Action.SHOW_CLUB;
        }
        else if (path.matches("/?clubs/edit/?$")){
            action = Action.EDIT_CLUB;
        }
        else {
            throw new RuntimeException("Unknown route: "+request.getServletPath());
        }
        return action;
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
        ALL_PLAYERS, SHOW_PLAYER, EDIT_PLAYER, NEW_PLAYER,
        ALL_MATCHES, SHOW_MATCH, EDIT_MATCH, NEW_MATCH,
        ALL_CLUBS, SHOW_CLUB, EDIT_CLUB, NEW_CLUB
    }
}
