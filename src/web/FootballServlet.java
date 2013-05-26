package web;

import beans.Dao;
import entities.Club;
import entities.Player;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
        Player player = dao.createPlayer("DIMA");
        Club club = dao.createClub("MU");
        club.addPlayer(player);
        dao.save(club);
        player = dao.getPlayer(player.getId());
        ServletOutputStream out = response.getOutputStream();
        out.print(player.getId()+":"+player.getName()+", club: "+player.getClub().getName());
    }

    private void loadResources(){
        try {
            InitialContext ic = new InitialContext();
//            Object o = ic.list("");
            dao = (Dao)ic.lookup(Dao.class.getName());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
