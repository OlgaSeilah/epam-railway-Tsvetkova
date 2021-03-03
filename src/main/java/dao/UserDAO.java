package dao;

import dao.interfaces.UserDaoInterface;
import entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserDAO extends ConnectionToDB  implements UserDaoInterface { //TODO переопределить туСтринг?

    @Override
    public User create(User newUser)  {
        String sqlRequest = "INSERT INTO passengers (login, pass, name, surname) VALUES (?, ?, ?, ?)";
        try {
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getName());
            preparedStatement.setString(4, newUser.getSurname());
            preparedStatement.executeUpdate();
            return newUser;

        } catch (SQLException e) {
            System.out.println("Пользователь с таким логином уже существует, пожалуйста, попробуйте заново");

        }
        return null;
    }

    @Override
    public void update(User obj) throws SQLException {

    }

    @Override
    public void delete(User deleteUser) throws SQLException {
        String sqlRequest = "DELETE FROM passengers WHERE login = '" + deleteUser.getLogin() + "';";
        try {
            conn = DriverManager.getConnection(url, properties);
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlRequest);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public User read(String login) throws SQLException {
        String sqlRequest = "SELECT * FROM passengers WHERE login = ?";
        try {
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                String surname = result.getString("surname");
                String password = result.getString("pass");
                return new User(name, surname, login, password);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String,String> readAllPassNames() throws SQLException {
        try {
            conn = DriverManager.getConnection(url, properties);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select * from passengers");
            Map<String,String> loginsAndNames = new HashMap<>();
            while (result.next()) {
                String passLogin = result.getString("login");
                String passName = result.getString("name");
                loginsAndNames.put(passLogin, passName);
            }

            return loginsAndNames;

        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }






}
