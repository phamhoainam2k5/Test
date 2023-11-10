package com.example.rental_house_project.Controller;

import com.example.rental_house_project.Model.User;
import com.example.rental_house_project.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("current-password");
        String newPassword = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirm-password");

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        UserService userService = new UserService();
        String oldPassword = String.valueOf(userService.getUser(username).getPassword());

        if (password != null || newPassword != null || confirmPassword != null) {
            if (password.equals(oldPassword)) {
                if (!password.equals(newPassword)) {
                    if (newPassword.length() > 6 || newPassword.length() < 32) {
                        if (confirmPassword.equals(newPassword)) {
                            try {
                                userService.updatePassword(username, newPassword);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            req.getRequestDispatcher("login.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("message", "Mật khẩu mới và mật khẩu xác nhận không khớp");
                            req.getRequestDispatcher("change-password.jsp").forward(req, resp);
                        }
                    } else {
                        req.setAttribute("message", "Mật khẩu mới phải có từ 6-32 ký tự");
                        req.getRequestDispatcher("change-password.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("message", "Mật khẩu mới phải khác với mật khẩu cũ");
                    req.getRequestDispatcher("change-password.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "Mật khẩu cũ không chính xác");
                req.getRequestDispatcher("change-password.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Tất cả các trường là bắt buộc");
            req.getRequestDispatcher("change-password.jsp").forward(req, resp);
        }
    }
}
