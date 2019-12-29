<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sharing system</title>
</head>
<body>
    <p> Welcome in your disk! </p>
    <p><%
        String[][] dirs = (String[][])request.getAttribute("files");
        for(String[] nameOfDirectory : dirs){
            for(String filesInDirectory : nameOfDirectory) {
                out.println("<a href=\"/download?file=" + filesInDirectory + "\">" + filesInDirectory + "</a></br>");
            }
        }
    %></p>
    <form action="upload" method="post" enctype = "multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="Upload" />
    </form>

    <form action="sharing" method="post">
        Give access: <select name="user">
        <%
            ArrayList<String> users = (ArrayList<String>)request.getAttribute("users");
            for(String tmp : users) {
                if(!tmp.equals(session.getAttribute("userName"))){
                     out.println("<option>" + tmp + "</option>");
                 }
            }
        %>
        </select>
        <input type="submit" value="Share" />
    </form>
    <form action="search" method="get">
        <input type="search" placeholder="Поиск файла" name="searchRequest">
        <input type="submit" value="Search">
    </form>
</body>
</html>
