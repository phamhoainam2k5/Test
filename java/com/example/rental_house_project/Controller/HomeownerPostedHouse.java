package com.example.rental_house_project.Controller;

import com.example.rental_house_project.Model.House;
import com.example.rental_house_project.Service.HouseService;
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

@WebServlet(name = "HomeownerPostedHouse", urlPatterns = "/HomeownerPostedHouse")

public class HomeownerPostedHouse extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HouseService homeowner = new HouseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "search":
                    searchHousesByStatus(req, resp);
                    break;
                case "onRoom":
                    searchByStatusRooms(req, resp);
                    break;
                case "offRoom":
                    searchByStatusRoomOutOfRoom(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                default:
                    listHouse(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void searchByStatusRoomOutOfRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String status = req.getParameter("status");
        List<House> list = homeowner.searchByStatusRooms(status);
        req.setAttribute("list", list);
        req.getRequestDispatcher("searchStatus.jsp").forward(req, resp);
    }

    private void searchByStatusRooms(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String status = req.getParameter("status");
        List<House> list = homeowner.searchByStatusRoomOutOfRoom(status);
        req.setAttribute("list", list);
        req.getRequestDispatcher("searchStatus.jsp").forward(req, resp);
    }

    private void searchHousesByStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String houseName = req.getParameter("houseName");
        List<House> list = homeowner.searchByName(houseName);
        req.setAttribute("list", list);
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    private void listHouse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int recordsPerPage = 10;
        int currentPage = 1;
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        List<House> list = homeowner.showAllHouse();
        int totalRecords = list.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        int startRecord = (currentPage - 1) * recordsPerPage;
        int endRecord = Math.min(startRecord + recordsPerPage, totalRecords);
        List<House> sublistUser = list.subList(startRecord, endRecord);
        req.setAttribute("list", sublistUser);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);

        req.setAttribute("list", list);
        req.getRequestDispatcher("properties.jsp").forward(req, resp);
    }



}