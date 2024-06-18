package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

@Component
public class UserDao {
    public static int userIdCount;

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    //private static final String db.driver = com.mysql.cj.jdbc.Driver
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Master";

    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from Users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            user = new User();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);

        return user;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (id,name, surname, age) values (?,?,?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(int id, User updateUser) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name=?,set surname=?,set age=? where id=?");
            preparedStatement.setString(1, updateUser.getName());
            preparedStatement.setString(2, updateUser.getSurname());
            preparedStatement.setInt(3, updateUser.getAge());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        User userToBeUpdate = getUserById(id);
//        userToBeUpdate.setName(updateUser.getName());
//        userToBeUpdate.setSurname(updateUser.getSurname());
//        userToBeUpdate.setAge(updateUser.getAge());
    }

    public void deleteUser(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from users where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

