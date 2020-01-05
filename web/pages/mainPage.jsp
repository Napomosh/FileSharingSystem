<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sharing system</title>
    <link rel="stylesheet" href="/styles/style.css">
</head>
<body>
    <p> Welcome in your disk! </p>
    <ul class="tree" id="tree">
        <% String[][] dirs = (String[][])request.getAttribute("files");
            for(String[] nameOfDirectory : dirs){
                out.println("<li>" + nameOfDirectory[0] + "<ul>");

                for(int i = 1; i < nameOfDirectory.length; i++){
                    out.print("<li> <a href=\"/download?file=" + nameOfDirectory[i] + "&directory=" +
                            nameOfDirectory[0] + "\">" + nameOfDirectory[i] + "</a></li>");
                }

                out.println("</ul></li>");
            } %>
    </ul>

    <form action="upload" method="post" enctype = "multipart/form-data">
        <input type="file" name="file" />
        <select name="dirForUpload">
        <%
            ArrayList<String> editableDirs = (ArrayList<String>)request.getAttribute("dirs");
            for(String tmp : editableDirs) {
                out.println("<option>" + tmp + "</option>");
            }
        %>
        <input type="submit" value="Upload" />
    </form>

    <form action="sharing" method="post">
        Give access:
        <select name="user">
        <%
            ArrayList<String> users = (ArrayList<String>)request.getAttribute("users");
            for(String tmp : users) {
                if(!tmp.equals(session.getAttribute("userName"))){
                     out.println("<option>" + tmp + "</option>");
                 }
            }
        %>
        </select>
        <input type="radio" name="modification" value="onlyRead" checked /> Only read
        <input type="radio" name="modification" value="readAndMod" /> Read and modification
        <input type="submit" value="Share" />
    </form>

    <form action="search" method="get">
        <input type="search" placeholder="Поиск файла" name="searchRequest">
        <input type="submit" value="Search">
    </form>

    <a href="/logout">Log out</a>

    <script src="/scripts/viewDirectories.js"></script>
</body>
</html>
