package controller;

import auxiliary.WorkWithDirectory;

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
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("userName") == null){
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }
        else {
            PrintWriter writer = new PrintWriter(resp.getWriter());
            String fileForDownload = req.getParameter("file");
            String nameOfDir = req.getParameter("directory");
            String userName = (String) session.getAttribute("userName");
            if (!WorkWithDirectory.hasUserThisDirectory(userName, nameOfDir)) {
                String path = req.getContextPath() + "/main";
                resp.sendRedirect(path);
            }

            FileInputStream file = new FileInputStream("C:\\java\\fileSharingSystem\\dirs\\" + nameOfDir + "\\"
                    + fileForDownload);

            resp.setContentType("APPLICATION/OCTET-STREAM");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileForDownload + "\"");

            int symbol;
            while ((symbol = file.read()) != -1) {
                writer.write(symbol);
            }
            writer.close();
            file.close();
        }
    }
}
