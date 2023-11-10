package com.example.rental_house_project.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Lấy session hiện tại (không tạo mới nếu chưa tồn tại)
        if (session != null) {
            session.invalidate(); // Huỷ session
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp"); // Chuyển hướng về trang đăng nhập
    }
}
