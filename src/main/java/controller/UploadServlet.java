package controller;

import auxiliary.WorkWithDirectory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("userName") == null){
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }
        else {
            Part file = req.getPart("file");
            String directoryForUpload = req.getParameter("dirForUpload");

            String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();

            InputStream fileContent = file.getInputStream();

            File uploadedFile = new File("C:\\java\\fileSharingSystem\\dirs\\" + directoryForUpload + "\\" + fileName);

            FileOutputStream out = new FileOutputStream(uploadedFile);
            out.write(fileContent.readAllBytes());

            out.close();
            fileContent.close();

            String path = req.getContextPath() + "/main";
            resp.sendRedirect(path);
        }
    }
}
