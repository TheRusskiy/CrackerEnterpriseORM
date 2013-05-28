package web;

import entities.Club;
import entities.Match;
import entities.Player;

import java.util.Date;

/**
 * Author: TheRusskiy
 * Date: 28.05.13
 * Time: 2:37
 */
public class JspUtil {
    public static String clubLink(Club club){
        String link = "";
        if (club==null){
            link+="Unemployed";
        } else {
            link+="<a ";
            link+="href='";
            link+= FootballServlet.Action.SHOW_CLUB.getRequestURI();
            link+="?id="+club.getId();
            link+="'>";
            link+=club.getName();
            link+="</a>";
        }
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
    public static String detailsLink(Club player){
        String link = "<a ";
        link+="href='";
        link+= FootballServlet.Action.SHOW_CLUB.getRequestURI();
        link+="?id="+player.getId();
        link+="'>";
        link+="Details";
        link+="</a>";
        return link;
    }
    public static String detailsLink(Match match){
        String link = "<a ";
        link+="href='";
        link+= FootballServlet.Action.SHOW_MATCH.getRequestURI();
        link+="?id="+match.getId();
        link+="'>";
        link+="Details";
        link+="</a>";
        return link;
    }
    public static String clubOption(Club club){
        String id = "0";
        String name = "Unemployed";
        if (club!=null) {
            id = String.valueOf(club.getId());
            name = club.getName();
        }
        String option = "<option ";
        option+="value='"+id+"'";
        option+=">";
        option+=name;
        option+="</option>";
        return option;
    }
    public static String formatDate(Date date){
        String result="";
        String month = ""+(date.getMonth()+1);
        if (month.length()==1) month="0"+month;
        String day = ""+(date.getDate());
        if (day.length()==1) day="0"+day;
        String year = ""+(date.getYear()+1900);
        result+=year+"-"+month+"-"+day;
        return result;
    }
    public static Date formatDate(String text){
        String ys = text.substring(0, 4);
        String ms = text.substring(5, 7);
        String ds = text.substring(8, 10);
        Date date = new Date();
        int year = Integer.parseInt(ys);
        int month = Integer.parseInt(ms);
        int day = Integer.parseInt(ds);
        date.setYear(year-1900);
        date.setMonth(month-1);
        date.setDate(day);
        return date;
    }
}
