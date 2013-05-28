<%@ page import="entities.Club" %>
<%@ page import="entities.Player" %>
<%@ page import="java.util.Collection" %>
<%@ page import="web.FootballServlet" %>
<%@ page import="static web.JspUtil.*" %>
<%--
  User: TheRusskiy
  Date: 27.05.13
  Time: 1:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Collection<Player> players = (Collection<Player>) request.getAttribute("players");%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_list">
    <table class="table table-striped table-bordered">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Position</th>
            <th>Club</th>
            <th></th>
        </tr>
        <% for(Player p: players){
            out.print("<tr>");
            out.print("<td>"+p.getId()+"</td>");
            out.print("<td>"+p.getName()+"</td>");
            out.print("<td>"+p.getPosition()+"</td>");
            out.print("<td>"+clubLink(p.getClub())+"</td>");
            out.print("<td>"+detailsLink(p)+"</td>");
            out.print("</tr>");
        }
        if (players.size()==0){ out.print("<td>NO PLAYERS FOUND</td>");}
        %>
    </table>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>