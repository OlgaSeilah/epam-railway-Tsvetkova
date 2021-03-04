package dao;

import dao.interfaces.RequestDaoInterface;
import entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Request read(Integer requestId) throws SQLException {
        String sqlRequest = "SELECT * FROM requests WHERE request_id=?";
        try {
            String passengerLogin = null;
            String startStation = null;
            String destinationStation = null;
            Request requestForEdit = new Request(passengerLogin, startStation, destinationStation);
            conn = DriverManager.getConnection(url,properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passengerLogin = resultSet.getString("passenger_login");
                startStation = resultSet.getString("start_station_name");
                destinationStation = resultSet.getString("dest_station_name");
                requestForEdit.setPassengerLogin(passengerLogin);
                requestForEdit.setStartStation(startStation);
                requestForEdit.setDestinationStation(destinationStation);
            }
            return requestForEdit;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Request requestForEdit) throws SQLException {
        String sqlRequest = "UPDATE requests SET start_station_name=?, dest_station_name=? WHERE request_id=?";

        try {
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, requestForEdit.getStartStation());
            preparedStatement.setString(2, requestForEdit.getDestinationStation());
            preparedStatement.setInt(3, requestForEdit.getRequestId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Request delRequest) throws SQLException {
        String sqlRequest = "DELETE FROM requests WHERE passenger_login = ? and start_station_name = ? and dest_station_name = ?";
        try {
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, delRequest.getPassengerLogin());
            preparedStatement.setString(2, delRequest.getStartStation());
            preparedStatement.setString(3, delRequest.getDestinationStation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, ArrayList<Integer>> getListOfEqualsRequestsHavingByOnePass(Request request) {
        String sqlRequest = "SELECT * FROM requests WHERE passenger_login = ?";
        try {
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, request.getPassengerLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> ids = new ArrayList<>();
            HashMap<String,ArrayList<Integer>> listOfEqualsRequests = new HashMap<>();

            while (resultSet.next()) {
                String passenger_login = resultSet.getString("passenger_login");
                int requestId = resultSet.getInt("request_id");
                ids.add(requestId);
                listOfEqualsRequests.put(passenger_login, ids);
            }
                return new HashMap<>(listOfEqualsRequests);

        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }


}
