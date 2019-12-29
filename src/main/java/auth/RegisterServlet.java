package auth;

import auxiliary.WorkWithUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (!WorkWithUsers.isUserRegistered(login)) {
                WorkWithUsers.addNewUser(login, password);

                String path = req.getContextPath() + "/login";
                resp.sendRedirect(path);
            } else {
                String path = req.getContextPath() + "/startPages/repeatRegister.jsp";
                resp.sendRedirect(path);
            }
    }
}
