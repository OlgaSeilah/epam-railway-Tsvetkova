package dao;

import entity.User;

import java.sql.*;

public class UserDAO extends ConnectionToDB  implements UserDaoInterface {

    @Override
    public User create(User newUser) throws SQLException {
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
//            System.out.println(e.getMessage());
            System.out.println("Ползователь с таким логином уже существует, пожалуйста, попробуйте заново");

        }
        return null;
    }

    @Override
    public void update(User obj) throws SQLException {

    }

    @Override
    public void delete(User obj) throws SQLException {

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
//    public Passenger getPassengerByLoginPassword(String login, String password) throws SQLException {
//        try {
//            Connection conn = DriverManager.getConnection(url, properties);
//            Statement statement = conn.createStatement();
//            ResultSet result = statement.executeQuery("select * from passengers where login = '" + login + "' and pass = '" + password +  "';");
//            if (result.next()) {
//                int id = result.getInt("id");
//                String name = result.getString("name");
//                String surname = result.getString("surname");
//                return new Passenger(id, name, surname, login, password);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }


        }
