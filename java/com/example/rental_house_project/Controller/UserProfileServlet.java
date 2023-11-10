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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/profileUser")
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService product = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "edit":
                    updateProfileUser(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                try {
                    showEditProfileUser(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    showUserInformation(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void updateProfileUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String urlImage = request.getParameter("urlImage");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        User user = new User(id, username, urlImage, fullName, address, phone);
        product.updateProfileUser(user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Profile/editProfileUser.jsp");
        dispatcher.forward(request, response);

    }

    private void showEditProfileUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = product.showEditProfileUser(id);
        req.setAttribute("user", user);
        System.out.println(user);
        req.getRequestDispatcher("Profile/editProfileUser.jsp").forward(req,resp);
    }

    private void showUserInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<User> listUser = product.showUserInformation();
        req.setAttribute("user", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Profile/showProfileUser.jsp");
        dispatcher.forward(req, resp);
    }
}
