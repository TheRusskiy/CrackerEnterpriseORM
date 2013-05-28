<%@ page import="entities.Club" %>
<%@ page import="entities.Player" %>
<%@ page import="java.util.Collection" %>
<%@ page import="web.FootballServlet" %>
<%@ page import="static web.JspUtil.*" %>
<%@ page import="entities.Match" %>
<%--
  User: TheRusskiy
  Date: 27.05.13
  Time: 1:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Collection<Match> matches = (Collection<Match>) request.getAttribute("matches");%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_list">
    <table class="table table-striped table-bordered">
        <tr>
            <th>ID</th>
            <th>Home</th>
            <th>Guest</th>
            <th>Date</th>
            <th></th>
        </tr>
        <% for(Match m: matches){
            out.print("<tr>");
            out.print("<td>"+m.getId()+"</td>");
            out.print("<td>"+clubLink(m.getHome())+"</td>");
            out.print("<td>"+clubLink(m.getGuest())+"</td>");
            out.print("<td>"+formatDate(m.getDate())+"</td>");
            out.print("<td>"+detailsLink(m)+"</td>");
            out.print("</tr>");
        }
        if (matches.size()==0){ out.print("<td>NO PLAYERS FOUND</td>");}
        %>
    </table>
    <form action="<%=FootballServlet.Action.NEW_MATCH_GET.getRequestURI()%>" method="get">
        <input class="btn btn-primary btn-large" type="submit" value="New Match"/>
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>