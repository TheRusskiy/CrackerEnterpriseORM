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
    <form action="<%=FootballServlet.Action.NEW_MATCH_POST.getRequestURI()%>" method="post">
        <label for="date">Date</label>
        <input id="date" type="text" name="date" value="<%=""%>"/>
        <label for="home">Home</label>
        <select id="home" name="home">
            <%
                for(Club c: availableClubs){
                    out.print(clubOption(c));
                }
            %>
        </select>
        <label for="guest">Guest</label>
        <select id="guest" name="guest">
            <%
                for(Club c: availableClubs){
                    out.print(clubOption(c));
                }
            %>
        </select>
        <br>
        <input class="btn btn-success" type="submit" value="Create" name="create">
    </form>
    <form action="<%=FootballServlet.Action.ALL_MATCHES.getRequestURI()%>" method="get">
        <input class="btn btn-danger" type="submit" value="Cancel" name="cancel" data-confirm="Sure?">
    </form>
</div>
</body>
</html>
<jsp:include page="/_footer.jsp"/>