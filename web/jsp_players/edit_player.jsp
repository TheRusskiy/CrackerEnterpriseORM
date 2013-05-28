<%@ page import="entities.Club" %>
<%@ page import="entities.Player" %>
<%@ page import="static web.JspUtil.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="web.FootballServlet" %>
<%--
  User: TheRusskiy
  Date: 28.05.13
  Time: 2:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Player p = (Player) request.getAttribute("player");
    Collection<Club> availableClubs = (Collection<Club>) request.getAttribute("clubs");
%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_edit">
    <form action="<%=FootballServlet.Action.EDIT_PLAYER_POST.getRequestURI()%>" method="post">
        <label for="id">ID</label>
            <input type="hidden" name="id" value="<%=p.getId()%>"/>
            <input id="id" type="text" name="id" readonly value="<%=p.getId()%>"/>
        <label for="name">Name</label>
            <input id="name" type="text" name="name" value="<%=p.getName()%>"/>
        <label for="position">Position</label>
            <input id="position" type="text" name="position" value="<%=p.getPosition()%>"/>
        <label for="club">Club</label>
            <select id="club" name="club">
                <%
                    if (p.getClub()!=null) out.print(clubOption(p.getClub()));
                    for(Club c: availableClubs){
                        if (p.getClub()!=null&& c.getId().equals(p.getClub().getId()))continue;
                        out.print(clubOption(c));
                    }
                %>
            </select>
        <br>
        <input class="btn btn-warning" type="submit" value="Save" name="save">
    </form>
    <form action="<%=FootballServlet.Action.EDIT_PLAYER_DELETE.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=p.getId()%>"/>
        <input class="btn btn-danger" type="submit" value="Delete" name="delete" data-confirm="Sure?">
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>