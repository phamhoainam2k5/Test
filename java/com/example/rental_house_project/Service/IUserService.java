package com.example.rental_house_project.Service;


import com.example.rental_house_project.Model.House;
import com.example.rental_house_project.Model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
     void insertUser(User user) throws ClassNotFoundException;

     boolean checkEmail(String username);

    void updateStatusForUser(int id) throws SQLException, ClassNotFoundException;

    void updateLockStatusForUser(int id) throws SQLException, ClassNotFoundException;

    List<User> showAccUser() throws SQLException, ClassNotFoundException;

    boolean updateProfileUser(User user) throws ClassNotFoundException, SQLException;

    boolean updateProfileProduct(User user) throws ClassNotFoundException, SQLException;
}
