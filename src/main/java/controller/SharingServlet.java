package controller;

import auxiliary.WorkWithDirectory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SharingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dirForSharing = (String)req.getSession().getAttribute("userName");
        String newUser = req.getParameter("user");

        if(!WorkWithDirectory.hasUserThisDirectory(newUser, dirForSharing)){
            WorkWithDirectory.addDirectoryToUser(dirForSharing, newUser);
        }
    }
}
