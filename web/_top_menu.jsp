<%@ page import="web.FootballServlet" %>
<%--
  User: TheRusskiy
  Date: 28.05.13
  Time: 1:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- NAVBAR
================================================== -->
<div class="navbar-wrapper common-text">
    <!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
    <div class="container">

        <div class="navbar navbar">
            <div class="navbar-inner">
                <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="<%= FootballServlet.Action.HOME.getRequestURI() %>">Football App</a>
                <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="<%= FootballServlet.Action.HOME.getRequestURI()%>">Home</a></li>
                        <li><a href="<%= FootballServlet.Action.ALL_CLUBS.getRequestURI() %>">Clubs</a></li>
                        <li><a href="<%= FootballServlet.Action.ALL_PLAYERS.getRequestURI() %>">Players</a></li>
                        <li><a href="<%= FootballServlet.Action.ALL_MATCHES.getRequestURI() %>">Matches</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->

    </div> <!-- /.container -->
</div><!-- /.navbar-wrapper -->