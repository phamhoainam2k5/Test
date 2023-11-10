package com.example.rental_house_project.Controller;

import com.example.rental_house_project.Model.User;
import com.example.rental_house_project.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");


        boolean isValid = true;

        if (username.trim().isEmpty()) {
            isValid = false;
            req.setAttribute("usernameError", "Vui lòng nhập tên đăng nhập");
        }

        if (phone.trim().isEmpty()) {
            isValid = false;
            req.setAttribute("phoneError", "Vui lòng nhập số điện thoại");
        }

        if (password.trim().isEmpty()) {
            isValid = false;
            req.setAttribute("passwordError", "Vui lòng nhập mật khẩu");
        } else if (password.length() < 6 || password.length() > 32) {
            isValid = false;
            req.setAttribute("passwordError", "Mật khẩu phải có từ 6-32 ký tự");
        }
        if (confirm.trim().isEmpty()) {
            isValid = false;
            req.setAttribute("confirmPasswordError", "Vui lòng xác nhận mật khẩu");
        } else if (!confirm.equals(password)) {
            isValid = false;
            req.setAttribute("confirmPasswordError", "Mật khẩu xác nhận không khớp");
        }

        boolean isUsernameExists = checkUsernameExists(username);

        if (isUsernameExists) {
            isValid = false;
            req.setAttribute("usernameError", "Tên đăng nhập đã tồn tại. Vui lòng sử dụng tên đăng nhập khác.");
        }
        boolean checkLandlord = Boolean.parseBoolean(req.getParameter("checkLandlord"));
        if (checkLandlord) {
            if (isValid) {
                UserService userService = new UserService();
                User user = new User(username, phone, password);
                try {
                    userService.insertLandlord(user);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                // Chuyển hướng đến trang đăng ký lại
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } else {
            if (isValid) {
                // Tạo tài khoản và lưu vào cơ sở dữ liệu (các bước xử lý khác)
                UserService userService = new UserService();
                User user = new User(username, phone, password);
                try {
                    userService.insertUser(user);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                // Chuyển hướng đến trang đăng ký lại
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        }
    }
    private boolean checkUsernameExists(String username) {
        UserService userService = new UserService();
        return userService.checkUsernameExist(username);
    }
}

