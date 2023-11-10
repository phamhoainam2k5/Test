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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/home-page")
public class ListFiveHouseServlet extends HttpServlet {
    private HouseService service;

    @Override
    public void init() throws ServletException {
       service = new HouseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<House> houses = service.showFiveHouse();
        req.setAttribute("houses", houses);
        RequestDispatcher dispatcher = req.getRequestDispatcher("home-page.jsp");
        dispatcher.forward(req, resp);


    }
}
