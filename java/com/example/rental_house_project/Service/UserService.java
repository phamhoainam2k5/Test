package com.example.rental_house_project.Service;


import com.example.rental_house_project.Model.House;
import com.example.rental_house_project.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserService implements IUserService {
    private String url = "jdbc:mysql://localhost:3306/homerental";
    private String user = "root";
    private String password = "anhnam2005";

    private static final String INSERT_USER = "insert into user (urlImage, username, phone, password,numberHouseForRent,userType,status) values (?,?,?,?,?,?,?);";
    private static final String UPDATE_USERS_SQL = "update user set username = ?,urlImage= ?, fullName =?, address =?,phone=?  where id = ?;";
    private static final String UPDATE_PASSWORD = "UPDATE user SET password = ? WHERE username = ?;";
    private static final String UPDATE_PROFILE = "UPDATE user SET urlImage = ?, fullName = ?, phone = ?, address =? where username = ?;";
    private static final String SELECT_USER_BY_ID = "select username,urlImage,fullName,address,phone from user where id =?";
    private static final String SELECT_USER_EXIST = "SELECT COUNT(*) FROM user WHERE username = ?";
    private static final String SELECT_USER_TYPE = "SELECT userType FROM user WHERE username = ?;";
    private static final String SELECT_USER = "SELECT * FROM user where username = ?";
    private static final String CHECK_MAIL = "SELECT COUNT(*) FROM user WHERE username = ?";
    private static final String SELECT_ALL_USER = "select * from user where userType='User';";
    private static final String SELECT_ALL_Landlord = "select * from user where userType='Landlord';";
    private static final String SELECT_ALL_ACCUSER = "select id,username,urlImage,fullName,address,phone from user";


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
    @Override
    public void insertUser(User user) throws ClassNotFoundException {
        Connection connection = connection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, "https://i.bloganchoi.com/bloganchoi.com/wp-content/uploads/2022/02/avatar-trang-y-nghia.jpeg?fit=512%2C20000&quality=95&ssl=1");
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, "User");
            preparedStatement.setString(7, "Đang chờ");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertLandlord(User user) throws ClassNotFoundException {
        Connection connection = connection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, "https://i.bloganchoi.com/bloganchoi.com/wp-content/uploads/2022/02/avatar-trang-y-nghia.jpeg?fit=512%2C20000&quality=95&ssl=1");
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, "Landlord");
            preparedStatement.setString(7, "Đang chờ");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean checkEmail(String username) {
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_MAIL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateStatusForUser(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection().prepareStatement("update user set status = 'Đang hoạt động' where id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connection().close();
    }
    @Override
    public void updateLockStatusForUser(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection().prepareStatement("update user set status = 'Khóa' where id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connection().close();
    }
    @Override
    public boolean updateProfileUser(User user) throws ClassNotFoundException, SQLException {
        PreparedStatement statement = connection().prepareStatement(UPDATE_USERS_SQL);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getUrlImage());
        statement.setString(3, user.getFullName());
        statement.setString(4, user.getAddress());
        statement.setString(5, user.getPhone());
        statement.setInt(6, user.getId());
        statement.executeUpdate();
        statement.close();
        return false;
    }
    @Override
    public boolean updateProfileProduct(User user) throws ClassNotFoundException, SQLException {
        PreparedStatement statement = connection().prepareStatement(UPDATE_USERS_SQL);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getUrlImage());
        statement.setString(3, user.getFullName());
        statement.setString(4, user.getAddress());
        statement.setString(5, user.getPhone());
        statement.setInt(6, user.getId());
        statement.executeUpdate();
        statement.close();
        return false;
    }


    @Override
    public List<User> showAccUser() throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection connection = connection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String fullName = rs.getString("fullName");
            String phone = rs.getString("phone");
            String status = rs.getString("status");
            list.add(new User(id, username, fullName, phone, status));
        }
        return list;
    }
    public List<User> showAccLandlord() throws ClassNotFoundException, SQLException {
        List<User> list = new ArrayList<>();
        Connection connection = connection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_Landlord);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String urlImg = rs.getString("urlImage");
            String fullName = rs.getString("fullName");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            int revenue = rs.getInt("revenue");
            int numberHouseForRent = rs.getInt("numberHouseForRent");
            String status = rs.getString("status");
            list.add(new User(id, username, urlImg, fullName,revenue, numberHouseForRent, address, phone, status));
        }
        return list;
    }
    public List<User> getUserList(int start, int end) {
        List<User> userList = new ArrayList<>();

        try (Connection connection = connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users LIMIT ?, ?")) {

            statement.setInt(1, start);
            statement.setInt(2, end - start);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String fullName = resultSet.getString("fullName");
                String phone = resultSet.getString("phone");
                String status = resultSet.getString("status");
                // Đọc thêm các trường khác nếu cần

                User user = new User(userId, userName);
                userList.add(user);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public int getTotalUsers() {
        int totalUsers = 0;

        try (Connection connection = connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users")) {

            if (resultSet.next()) {
                totalUsers = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return totalUsers;
    }
    public User showEditProfileUser(int id) throws SQLException, ClassNotFoundException {
        User user1 = null;
        PreparedStatement preparedStatement = connection().prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username");
            String urlImage = rs.getString("urlImage");
            String fullName = rs.getString("fullName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            user1 = new User(username, urlImage, fullName, address, phone);
        }
        return user1;
    }
    public List<User> showUserInformation() throws ClassNotFoundException, SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement statement = connection().prepareStatement(SELECT_ALL_ACCUSER);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String urlImage = rs.getString("urlImage");
            String fullName = rs.getString("fullName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            list.add(new User(id, username, urlImage, fullName, address, phone));
        }
        return list;
    }
    public void updatePassword(String username,String password) throws ClassNotFoundException {
        try (Connection connection = connection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD);) {
            statement.setString(1, password);
            statement.setString(2, username);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User showEditProfileProduct(int id) throws SQLException, ClassNotFoundException {
        User product = null;
        PreparedStatement preparedStatement = connection().prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username");
            String url_image = rs.getString("url_image");
            String full_name = rs.getString("full_name");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            product = new User(username, url_image, full_name, address, phone);

        }
        return product;
    }
    public List<User> showProductInformation() throws ClassNotFoundException, SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement statement = connection().prepareStatement(SELECT_ALL_ACCUSER);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String urlImage = rs.getString("urlImage");
            String fullName = rs.getString("fullName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            list.add(new User(id, username, urlImage, fullName, address, phone));
        }
        return list;
    }
    public void updateProfile(String username,String urlImage, String fullName, String phone, String address) {
        try (Connection connection = connection();
             PreparedStatement statement = connection().prepareStatement(UPDATE_PROFILE)) {
            statement.setString(1,urlImage);
            statement.setString(2, fullName);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setString(5, username);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean checkUsernameExist(String username) {
        try (Connection connection = connection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_EXIST)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkCredentials(String username, String password) {
        try {
            Connection conn = connection();
            String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
    public String checkUserType(String username) {
        try (Connection connection = connection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_TYPE);) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userType = resultSet.getString("userType");
                return userType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    public User getUser(String username) {
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection().prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String urlImage = rs.getString("urlImage");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                int revenue = rs.getInt("revenue");
                int numberHouseForRent = rs.getInt("numberHouseForRent");
                String phone = rs.getString("phone");
                String userType = rs.getString("userType");
                String status = rs.getString("status");

                User user = new User(id,username, password ,urlImage,fullname,address,phone,revenue,numberHouseForRent,userType,status);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

