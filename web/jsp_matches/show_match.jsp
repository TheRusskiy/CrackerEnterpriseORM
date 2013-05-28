<%@ page import="entities.Player" %>
<%@ page import="web.FootballServlet" %>
<%@ page import="static web.JspUtil.*" %>
<%@ page import="entities.Match" %>
<%--
  User: TheRusskiy
  Date: 28.05.13
  Time: 2:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Match m = (Match) request.getAttribute("match");%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_show">
    <table class="table table-striped table-bordered">
        <tr>
            <th>ID</th>
            <th>Home</th>
            <th>Guest</th>
            <th>Date</th>
        </tr>
        <%  out.print("<tr>");
            out.print("<td>"+m.getId()+"</td>");
            out.print("<td>"+clubLink(m.getHome())+"</td>");
            out.print("<td>"+clubLink(m.getGuest())+"</td>");
            out.print("<td>"+formatDate(m.getDate())+"</td>");
            out.print("</tr>");
        %>
    </table>
    <form action="<%=FootballServlet.Action.EDIT_MATCH_GET.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=m.getId()%>"/>
        <input class="btn btn-primary" type="submit" value="Edit" name="edit">
    </form>
    <form action="<%=FootballServlet.Action.EDIT_MATCH_DELETE.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=m.getId()%>"/>
        <input class="btn btn-danger" type="submit" value="Delete" name="delete" data-confirm="Sure?">
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>