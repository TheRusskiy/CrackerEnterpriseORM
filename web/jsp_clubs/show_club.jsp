<%@ page import="entities.Club" %>
<%@ page import="web.FootballServlet" %>
<%@ page import="static web.JspUtil.*" %>
<%--
  User: TheRusskiy
  Date: 28.05.13
  Time: 2:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Club c = (Club) request.getAttribute("club");
    String xml = (String) request.getAttribute("xml");
%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_show">
    <table class="table table-striped table-bordered">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Stadium</th>
        </tr>
        <%  out.print("<tr>");
            out.print("<td>"+ c.getId()+"</td>");
            out.print("<td>"+ c.getName()+"</td>");
            out.print("<td>"+ c.getStadium()+"</td>");
            out.print("</tr>");
        %>
    </table>
    <form action="<%=FootballServlet.Action.EDIT_CLUB_GET.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=c.getId()%>"/>
        <input class="btn btn-primary" type="submit" value="Edit" name="edit">
    </form>
    <form action="<%=FootballServlet.Action.EDIT_CLUB_DELETE.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=c.getId()%>"/>
        <input class="btn btn-danger" type="submit" value="Delete" name="delete" data-confirm="Sure?">
    </form>
</div>
<div class="data_xml">
    <label>
        XML Representation
        <textarea rows="7"><%=xml%></textarea>
    </label>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>