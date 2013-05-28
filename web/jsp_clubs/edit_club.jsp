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
    Club c = (Club) request.getAttribute("club");
%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_edit">
    <form action="<%=FootballServlet.Action.EDIT_CLUB_POST.getRequestURI()%>" method="post">
        <label for="id">ID</label>
            <input type="hidden" name="id" value="<%=c.getId()%>"/>
            <input id="id" type="text" name="id" readonly value="<%=c.getId()%>"/>
        <label for="name">Name</label>
            <input id="name" type="text" name="name" value="<%=c.getName()%>"/>
        <label for="position">Stadium</label>
            <input id="position" type="text" name="stadium" value="<%=c.getStadium()%>"/>
        <br>
        <input class="btn btn-warning" type="submit" value="Save" name="save">
    </form>
    <form action="<%=FootballServlet.Action.EDIT_CLUB_DELETE.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=c.getId()%>"/>
        <input class="btn btn-danger" type="submit" value="Delete" name="delete" data-confirm="Sure?">
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>