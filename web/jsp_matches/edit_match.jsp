<%@ page import="entities.Club" %>
<%@ page import="entities.Player" %>
<%@ page import="static web.JspUtil.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="web.FootballServlet" %>
<%@ page import="entities.Match" %>
<%--
  User: TheRusskiy
  Date: 28.05.13
  Time: 2:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Match m = (Match) request.getAttribute("match");
    Collection<Club> availableClubs = (Collection<Club>) request.getAttribute("clubs");
%>
<html>
<jsp:include page="/_head.jsp"/>
<body>
<jsp:include page="/_top_menu.jsp"/>
<div class="data_edit">
    <script>
        $(function() {
            $("#date").datepicker(
                    {
                        dateFormat: 'yy-mm-dd'
//                        defaultDate: '2010-01-01'
                    });
        });
    </script>
    <form action="<%=FootballServlet.Action.EDIT_MATCH_POST.getRequestURI()%>" method="post">
        <label for="id">ID</label>
            <input type="hidden" name="id" value="<%=m.getId()%>"/>
            <input id="id" type="text" name="id" readonly value="<%=m.getId()%>"/>
        <label for="date">Date</label>
            <input id="date" type="text" name="date" value="<%=formatDate(m.getDate())%>"/>
        <label for="home">Home</label>
            <select id="home" name="home">
                <%
                    out.print(clubOption(m.getHome()));
                    for(Club c: availableClubs){
                        if (c.getId().equals(m.getHome().getId()))continue;
                        out.print(clubOption(c));
                    }
                %>
            </select>
        <label for="guest">Guest</label>
        <select id="guest" name="guest">
            <%
                out.print(clubOption(m.getGuest()));
                for(Club c: availableClubs){
                    if (c.getId().equals(m.getGuest().getId()))continue;
                    out.print(clubOption(c));
                }
            %>
        </select>
        <br>
        <input class="btn btn-warning" type="submit" value="Save" name="save">
    </form>
    <form action="<%=FootballServlet.Action.EDIT_MATCH_DELETE.getRequestURI()%>" method="get">
        <input type="hidden" name="id" value="<%=m.getId()%>"/>
        <input class="btn btn-danger" type="submit" value="Delete" name="delete" data-confirm="Sure?">
    </form>
</div>

</body>
</html>
<jsp:include page="/_footer.jsp"/>