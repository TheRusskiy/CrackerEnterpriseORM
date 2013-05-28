<%@ page import="entities.Player" %>
<%@ page import="java.util.Collection" %>
<%@ page import="static web.JspUtil.*" %>
<%--
  User: TheRusskiy
  Date: 28.05.13
  Time: 2:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Player p = (Player) request.getAttribute("player");%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_show">
    <table class="table table-striped table-bordered">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Position</th>
            <th>Club</th>
        </tr>
        <%  out.print("<tr>");
            out.print("<td>"+p.getId()+"</td>");
            out.print("<td>"+p.getName()+"</td>");
            out.print("<td>"+p.getPosition()+"</td>");
            out.print("<td>"+clubLink(p.getClub())+"</td>");
            out.print("</tr>");
        %>
    </table>
    <%=editLink(p)%>
    <%--<%=deleteLink(p)%>--%>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>