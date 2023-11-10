package com.example.rental_house_project.Controller;

import com.example.rental_house_project.Model.User;
import com.example.rental_house_project.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/landlordlist", name = "LandlordList")
public class LandlordListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService users = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "active":
                    active(req,resp);
                    break;
                case "unActive" :
                    unActive(req,resp);
                    break;
                default:
                    listUser(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void unActive(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("userId"));
        users.updateLockStatusForUser(id);
        List<User> listUser = users.showAccLandlord();
        request.setAttribute("listLandlord", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("landlordlist.jsp");
        dispatcher.forward(request, response);
    }

    private void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("userId"));
        users.updateStatusForUser(id);
        List<User>listUser = users.showAccLandlord();
        request.setAttribute("listLandlord", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("landlordlist.jsp");
        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int page = 1; // Trang mặc định
        int limit = 10; // Số lượng dữ liệu trên mỗi trang

        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        int start = (page - 1) * limit;
        int end = start + limit;
        try {
            List<User> listLandlord = users.showAccLandlord();

            // Lấy tổng số người dùng từ UserService
            int totalUsers = users.getTotalUsers();

            // Tính toán tổng số trang dựa trên tổng số người dùng và số lượng dòng trên mỗi trang
            int totalPages = (int) Math.ceil((double) totalUsers / limit);


            request.setAttribute("listLandlord", listLandlord);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            RequestDispatcher dispatcher = request.getRequestDispatcher("landlordlist.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
