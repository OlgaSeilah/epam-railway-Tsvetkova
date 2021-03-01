package dao;

import entity.Request;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RequestDAO extends ConnectionToDB implements CRUDInterface<Request, String> {

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
    public Request read(String someId) throws SQLException {
        return null;
    }

    @Override
    public void update(Request obj) throws SQLException {

    }

    @Override
    public void delete(Request obj) throws SQLException {

    }

    public List<String> readThreeMostPopularStations() throws SQLException {
        String sqlRequest = "select dest_station_name from requests where station_name = ?";
        try {
            conn = DriverManager.getConnection(url,properties);
            Statement statement = conn.createStatement();
            statement.executeQuery(sqlRequest);

        } catch (SQLException e) {

        }
    }

        }
