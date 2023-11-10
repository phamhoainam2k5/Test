package com.example.rental_house_project.Controller;

import com.example.rental_house_project.Model.User;
import com.example.rental_house_project.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UpdateProfileServlet", urlPatterns = "/update-profile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String urlImage = req.getParameter("urlImage");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if (fullName != null && phone != null && address != null) {
            if (!isValidInput(fullName) || !isValidInput(phone) || !isValidInput(address)) {
                UserService userService = new UserService();
                userService.updateProfile(username, urlImage, fullName, phone, address);
                req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Không được dùng đến ký tự đặc biệt trong tất cả các trường !!!");
                req.getRequestDispatcher("update-profile.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Các trường Full name, Phone, Address không được để rỗng !!!");
            req.getRequestDispatcher("update-profile.jsp").forward(req, resp);
        }
    }
    private boolean isValidInput(String input) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return input.matches(regex);
    }
}
