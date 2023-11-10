package com.example.rental_house_project.Service;

import com.example.rental_house_project.Model.House;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseService {
    private String url = "jdbc:mysql://localhost:3306/homerental";
    private String user = "root";
    private String password = "anhnam2005";

    private static final String SELECT_ALL_HOUSE = "SELECT * FROM House;";
    private static final String SELECT_FIVE_HOUSE = "SELECT * FROM House ORDER BY revenue DESC LIMIT 5;";
    private static final String SELECT_All_HOUSE_OF_LANDLORD = "SELECT * FROM House INNER JOIN user ON House.userId = user.id WHERE user.id = ?;";
    private static final String SHOW_ALL_HOUSE = "select * from House";

    public Connection connection() throws ClassNotFoundException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public List<House> showAllHouseRental() throws ClassNotFoundException, SQLException {
        List<House> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection().prepareStatement(SELECT_ALL_HOUSE);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int houseId = rs.getInt("houseId");
            int userId = rs.getInt("userId");
            String imgHouse = rs.getString("imgHouse");
            String houseName = rs.getString("housename");
            String price = rs.getString("price");
            String timeRental = rs.getString("timeRental");
            String address = rs.getString("address");
            int revenue = rs.getInt("revenue");
            int numberBath = rs.getInt("numberBath");
            int numberBed = rs.getInt("numberBed");
            int width = rs.getInt("width");
            String describeHouse = rs.getString("describeHouse");
            String status = rs.getString("status");
            list.add(new House(houseId, userId, imgHouse, houseName, price, timeRental, address, revenue, numberBath, numberBed, width, describeHouse, status));
        }

        for (House house : list) {
            house.setPrice(String.format(house.getFormattedPrice()));
        }
        return list;
    }

    public List<House> showFiveHouse() {
        List<House> houses = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection().prepareStatement(SELECT_FIVE_HOUSE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int houseId = rs.getInt("houseId");
                int userId = rs.getInt("userId");
                String imgHouse = rs.getString("imgHouse");
                String houseName = rs.getString("housename");
                String price = rs.getString("price");
                String timeRental = rs.getString("timeRental");
                String address = rs.getString("address");
                int revenue = rs.getInt("revenue");
                int numberBath = rs.getInt("numberBath");
                int numberBed = rs.getInt("numberBed");
                int width = rs.getInt("width");
                String describeHouse = rs.getString("describeHouse");
                String status = rs.getString("status");
                houses.add(new House(houseId, userId, imgHouse, houseName, price, timeRental, address, revenue, numberBath, numberBed, width, describeHouse, status));
            }
            for (House house : houses) {
                house.setPrice(String.format(house.getFormattedPrice()));
            }
            return houses;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<House> getAllHomeOfLandlord(int id) throws ClassNotFoundException, SQLException {
        List<House> homes = new ArrayList<>();
        PreparedStatement preparedStatement = connection().prepareStatement(SELECT_All_HOUSE_OF_LANDLORD);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int houseId = rs.getInt("houseId");
            int userId = rs.getInt("userId");
            String imgHouse = rs.getString("imgHouse");
            String houseName = rs.getString("housename");
            String price = rs.getString("price");
            String timeRental = rs.getString("timeRental");
            String address = rs.getString("address");
            int revenue = rs.getInt("revenue");
            int numberBath = rs.getInt("numberBath");
            int numberBed = rs.getInt("numberBed");
            int width = rs.getInt("width");
            String describeHouse = rs.getString("describeHouse");
            String status = rs.getString("status");
            homes.add(new House(houseId, userId, imgHouse, houseName, price, timeRental, address, revenue, numberBath, numberBed, width, describeHouse, status));
        }

        for (House house : homes) {
            house.setPrice(String.format(house.getFormattedPrice()));
        }
        return homes;
    }


    public List<House> showAllHouse() throws ClassNotFoundException, SQLException {
        List<House> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection().prepareStatement(SHOW_ALL_HOUSE);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int houseId = rs.getInt("houseId");
            String houseName = rs.getString("houseName");
            String address = rs.getString("address");
            int numberBath = rs.getInt("numberBath");
            int numberBed = rs.getInt("numberBed");
            String describeHouse = rs.getString("describeHouse");
            String imgHouse = rs.getString("imgHouse");

            list.add(new House(houseId, houseName, address, numberBath, numberBed, describeHouse, imgHouse));
        }
        return list;
    }

    public List<House> searchByStatusRooms(String statusRoom) throws ClassNotFoundException, SQLException {
        List<House> list = new ArrayList<>();
        String query = "SELECT  houseName,address,numberBath,numberBed,describeHouse,imgHouse from House WHERE status ='Còn Phòng'";
        PreparedStatement statement = connection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String houseName = rs.getString("houseName");
            String address = rs.getString("address");
            int numberBath = rs.getInt("numberBath");
            int numberBed = rs.getInt("numberBed");
            String describeHouse = rs.getString("describeHouse");
            String imgHouse = rs.getString("imgHouse");

            list.add(new House(imgHouse,houseName, address, numberBath, numberBed, describeHouse));
        }
        return list;
    }


    public List<House> searchByName(String nameProduct) throws ClassNotFoundException, SQLException {
        List<House> list = new ArrayList<>();
        String query = "Select houseName,address,numberBath,numberBed,describeHouse,imgHouse from House where houseName like '%" + nameProduct + "%'";
        PreparedStatement statement = connection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String houseName = rs.getString("houseName");
            String address = rs.getString("address");
            int numberBath = rs.getInt("numberBath");
            int numberBed = rs.getInt("numberBed");
            String describeHouse = rs.getString("describeHouse");
            String imgHouse = rs.getString("imgHouse");

            list.add(new House(imgHouse,houseName, address, numberBath, numberBed, describeHouse));
        }
        return list;
    }

    public List<House> searchByStatusRoomOutOfRoom(String statusRoom) throws ClassNotFoundException, SQLException {
        List<House> list = new ArrayList<>();
        String query = "SELECT houseName,address,numberBath,numberBed,describeHouse,imgHouse from House WHERE status ='Hết Phòng'";
        PreparedStatement statement = connection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String houseName = rs.getString("houseName");
            String address = rs.getString("address");
            int numberBath = rs.getInt("numberBath");
            int numberBed = rs.getInt("numberBed");
            String describeHouse = rs.getString("describeHouse");
            String imgHouse = rs.getString("imgHouse");

            list.add(new House(imgHouse,houseName, address, numberBath, numberBed, describeHouse));
        }
        return list;
    }

}
