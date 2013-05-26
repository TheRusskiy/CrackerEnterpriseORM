<%--
  ~ Copyright © 2013 Ishkov Dmitry <therusskiy@gmail.com>
  ~ This work is free. You can redistribute it and/or modify it under the
  ~ terms of the Do What The Fuck You Want To Public License, Version 2,
  ~ as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
  --%>

<%--
  User: TheRusskiy
  Date: 10.04.13
  Time: 1:40
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/employees/_header.jsp"></jsp:include>
<body>
<h1>Initializing self-destruction...</h1>
<p>
    <b>Type:</b> <%= exception.getClass()%> <br>
    <b>Message:</b> <%= exception.getMessage()%> <br>
    <b>Cause:</b> <%= exception.getCause()%> <br>
    <b>Additional:</b><br> <%
                for(StackTraceElement element : exception.getStackTrace()){
                    out.print(element.toString());
                    out.print("<br>");
                }
                %>
</p>
</body>
<jsp:include page="/employees/_footer.jsp"></jsp:include>
</html>
