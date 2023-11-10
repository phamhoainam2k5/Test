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
import java.io.PrintWriter;
import java.util.regex.Pattern;

import static java.lang.System.out;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username-login");
        String password = request.getParameter("password-login");

        UserService userService = new UserService();

        boolean accountExist = userService.checkUsernameExist(username);
        boolean credentialsValid = userService.checkCredentials(username, password);
        String isUserType = userService.checkUserType(username);
        Pattern pattern = Pattern.compile("[*%,']");
        boolean containsSpecialCharacters = pattern.matcher(username).find();

        if (containsSpecialCharacters) {
            out.println("<script>alert('Username contains invalid characters'); resetFields();</script>");
        }

        if (accountExist) {
            if (credentialsValid) {
                User user = userService.getUser(username);

                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("id",user.getId());
                    session.setAttribute("username", username);
                    session.setAttribute("urlImage", user.getUrlImage());
                    session.setAttribute("fullname", user.getFullName());
                    session.setAttribute("phone", user.getPhone());
                    session.setAttribute("address", user.getAddress());
                    session.setAttribute("numberHouseForRent", user.getNumberHouseForRent());
                    if (isUserType.equals("Admin")) {
                        session.setAttribute("userType", "Admin");
                    } else if (isUserType.equals("User")) {
                        session.setAttribute("userType", "User");
                    } else {
                        session.setAttribute("userType", "Landlord");
                    }
                    response.sendRedirect("/home-page");
                } else {
                    request.setAttribute("error", "Failed to retrieve user information");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Username or password is incorrect");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Username not exist ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
