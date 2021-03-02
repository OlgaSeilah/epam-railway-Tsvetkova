package dao;

import entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO extends ConnectionToDB implements RequestDaoInterface {

    @Override
    public Request create(Request request) throws SQLException {
        String sqlRequest = "INSERT INTO requests (start_station_name, dest_station_name, passenger_login) VALUES (?, ?, ?)";
        conn = DriverManager.getConnection(url, properties);
        PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
        preparedStatement.setString(1,request.getStartStation());
        preparedStatement.setString(2,request.getDestinationStation());
        preparedStatement.setString(3,request.getPassengerLogin());
        preparedStatement.executeUpdate();
        return request;
    }


    @Override
    public List<String> readThreeMostPopularStations() throws SQLException {
        String sqlRequest = "SELECT dest_station_name FROM requests\n" +
                "    GROUP BY dest_station_name ORDER BY count(*) DESC LIMIT 3;";
        List<String> listOfPopularStations = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url,properties);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                listOfPopularStations.add(resultSet.getString("dest_station_name"));
            }
            return listOfPopularStations;

        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public Request read(String someId) throws SQLException {
        return null;
    }

    @Override
    public void delete(Request obj) throws SQLException {

    }

    @Override
    public void update(Request obj) throws SQLException {

    }
}
