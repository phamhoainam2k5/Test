package com.example.rental_house_project.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/submit-property")
public class SubmitPropertyServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form
        String houseName = request.getParameter("houseName");
        String address = request.getParameter("address");
        int bedrooms = Integer.parseInt(request.getParameter("bedrooms"));
        int bathrooms = Integer.parseInt(request.getParameter("bathrooms"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String rentalPeriod = request.getParameter("rentalPeriod");

        // Xử lý file ảnh

        // Lưu thông tin vào cơ sở dữ liệu hoặc thực hiện các thao tác khác

        // Chuyển hướng về trang thành công
        response.sendRedirect("home-page.html");
    }
}
