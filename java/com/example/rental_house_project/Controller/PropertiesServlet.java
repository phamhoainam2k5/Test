package com.example.rental_house_project.Controller;

import com.example.rental_house_project.Model.House;
import com.example.rental_house_project.Service.HouseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listHouse")
public class PropertiesServlet extends HttpServlet {
    HouseService service = new HouseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int pageSize = 9;
            int currentPage = 1;

            String pageParam = req.getParameter("page");
            if (pageParam != null) {
                currentPage = Integer.parseInt(pageParam);
            }

            List<House> houses = service.showAllHouseRental();

            int totalHouses = houses.size();
            int totalPages = (int) Math.ceil((double) totalHouses / pageSize);

            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalHouses);

            List<House> pagedList = houses.subList(startIndex, endIndex);

            req.setAttribute("pagedList", pagedList);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", currentPage);

            RequestDispatcher dispatcher = req.getRequestDispatcher("properties.jsp");
            dispatcher.forward(req, resp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
