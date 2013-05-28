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
<% Collection<Club> clubs = (Collection<Club>) request.getAttribute("clubs");%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_list">
    <table class="table table-striped table-bordered">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Stadium</th>
            <th></th>
        </tr>
        <% for(Club p: clubs){
            out.print("<tr>");
            out.print("<td>"+p.getId()+"</td>");
            out.print("<td>"+p.getName()+"</td>");
            out.print("<td>"+p.getStadium()+"</td>");
            out.print("<td>"+detailsLink(p)+"</td>");
            out.print("</tr>");
        }
        if (clubs.size()==0){ out.print("<td>NO CLUBS FOUND</td>");}
        %>
    </table>
    <form action="<%=FootballServlet.Action.NEW_CLUB_GET.getRequestURI()%>" method="get">
        <input class="btn btn-primary btn-large" type="submit" value="New Club"/>
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>