package web;

import entities.Club;
import entities.Player;

/**
 * Author: TheRusskiy
 * Date: 28.05.13
 * Time: 2:37
 */
public class JspUtil {
    public static String clubLink(Club club){
        String link = "<a ";
        link+="href='";
        link+= FootballServlet.Action.SHOW_CLUB.getRequestURI();
        link+="?id="+club.getId();
        link+="'>";
        link+=club.getName();
        link+="</a>";
        return link;
    }
    public static String detailsLink(Player player){
        String link = "<a ";
        link+="href='";
        link+= FootballServlet.Action.SHOW_PLAYER.getRequestURI();
        link+="?id="+player.getId();
        link+="'>";
        link+="Details";
        link+="</a>";
        return link;
    }
    public static String editLink(Player player){
        String link = "<a ";
        link+="href='";
        link+= FootballServlet.Action.EDIT_PLAYER_GET.getRequestURI();
        link+="?id="+player.getId();
        link+="'>";
        link+="Edit";
        link+="</a>";
        return link;
    }
    public static String clubOption(Club club){
        String option = "<option ";
        option+="value='"+club.getId()+"'";
        option+=">";
        option+=club.getName();
        option+="</option>";
        return option;
    }
}
