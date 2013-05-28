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
<% %>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_edit">
    <form action="<%=FootballServlet.Action.NEW_CLUB_POST.getRequestURI()%>" method="post">
        <label for="name">Name</label>
            <input id="name" type="text" name="name" value=""/>
        <label for="position">Stadium</label>
            <input id="position" type="text" name="stadium" value=""/>
        <br>
        <input class="btn btn-success" type="submit" value="Create" name="create">
    </form>
    <form action="<%=FootballServlet.Action.ALL_CLUBS.getRequestURI()%>" method="get">
        <input class="btn btn-danger" type="submit" value="Cancel" name="cancel" data-confirm="Sure?">
    </form>
</div>
<div class="data_xml">
    <form action="<%=FootballServlet.Action.NEW_CLUB_XML.getRequestURI()%>" method="post">
        <label>
            XML Representation
            <textarea name="xml" rows="7"></textarea>
        </label>
        <input class="btn btn-success" type="submit" value="Create" name="create">
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>