package controller;

import auxiliary.WorkWithDirectory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = (String)req.getSession().getAttribute("userName");
        String searchRequest = req.getParameter("searchRequest");

        ArrayList<String> resOfSearching = WorkWithDirectory.doSearch(userName, searchRequest);

        req.setAttribute("searchResult", resOfSearching);

        String path = "/pages/searching.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
