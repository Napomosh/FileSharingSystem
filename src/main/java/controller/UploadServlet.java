package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("file");
        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();

        InputStream fileContent = file.getInputStream();

        File uploadedFile =new File("C:\\java\\fileSharingSystem\\dirs\\" +
                req.getSession().getAttribute("userName") + "\\" + fileName);

        FileOutputStream out = new FileOutputStream(uploadedFile);
        out.write(fileContent.readAllBytes());

        out.close();
        fileContent.close();

//        for(Part part : req.getParts()){
//            try {
//                part.write(part.getSubmittedFileName());
//            }
//            catch (IOException e){
//                break;
//            }
//        }

//        String path = req.getContextPath() + "/pages/mainPage.jsp";
////        resp.sendRedirect("/controllerPack/main");

        String path = req.getContextPath() + "/main";
        resp.sendRedirect(path);
    }
}
