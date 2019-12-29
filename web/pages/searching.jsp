<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sharing system</title>
</head>
<body>
    <p>
        Search result
    </p>
    <p>
        <%
            ArrayList<String> resOfSearch = (ArrayList<String>)request.getAttribute("searchResult");
            for(String nameOfFoundedFile : resOfSearch){
                out.println("<a href=\"/download?file=" + nameOfFoundedFile + "\">" + nameOfFoundedFile + "</a></br>");
            }
        %>
    </p>
</body>
</html>
