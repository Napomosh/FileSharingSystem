package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getWriter());
        String fileForDownload = req.getParameter("file");
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");
        FileInputStream file = new FileInputStream("C:\\java\\fileSharingSystem\\dirs\\" + userName + "\\" + fileForDownload);

        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileForDownload + "\"");

        int symbol;
        while((symbol = file.read()) != -1){
            writer.write(symbol);
        }
        writer.close();
        file.close();
    }
}
