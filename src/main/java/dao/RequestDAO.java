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

//    @Override
//    public Request create(Request request) throws SQLException {
//        String sqlRequestFindStations = "SELECT station_name FROM stations WHERE station_name = ? AND station_name = ?";
//        String sqlRequestInsert = "INSERT INTO requests (start_station_name, dest_station_name, passenger_login) VALUES (?, ?, ?)";
//
//        String startStation = request.getStartStation();
//        String destStation = request.getDestinationStation();
//
//        try {
//            conn = DriverManager.getConnection(url, properties);
//            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequestFindStations);
//            preparedStatement.setString(1,request.getStartStation());
//            preparedStatement.setString(2,request.getDestinationStation());
//            ResultSet result = preparedStatement.executeQuery();
//            while (result.next()) {
//                startStation = result.getString("station_name");
//                destStation = result.getString("station_name");
//            }
//            PreparedStatement preparedStatementforUpdate = conn.prepareStatement(sqlRequestInsert);
//            preparedStatementforUpdate.setString(1,request.getStartStation());
//            preparedStatementforUpdate.setString(2,request.getDestinationStation());
//            preparedStatementforUpdate.setString(3,request.getPassengerLogin());
//            preparedStatementforUpdate.executeUpdate();
//            Request finRequest= new Request(startStation, destStation, request.getPassengerLogin());
//
//            finRequest.setStartStation(startStation);
//            finRequest.setDestinationStation(destStation);
//
//            return finRequest;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            System.out.println(startStation);
//            System.out.println(destStation);
//        }
//        return null;
////
////        PreparedStatement preparedStatement = conn.prepareStatement(sqlRequestInsert);
////        preparedStatement.setString(1,request.getStartStation());
////        preparedStatement.setString(2,request.getDestinationStation());
////        preparedStatement.setString(3,request.getPassengerLogin());
////        preparedStatement.executeUpdate();
////        return request;
//    }

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
    public void delete(Request delRequest) throws SQLException {
        String sqlRequest = "DELETE FROM requests WHERE passenger_login = ? and start_station_name = ? and dest_station_name = ?";
        try {
            System.out.println(delRequest.getPassengerLogin() + delRequest.getStartStation() + delRequest.getDestinationStation());
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, delRequest.getPassengerLogin());
            preparedStatement.setString(2, delRequest.getStartStation());
            preparedStatement.setString(3, delRequest.getDestinationStation());
            int res = preparedStatement.executeUpdate();
            System.out.println(res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, List<Integer>> getListOfEqualsRequestsHavingByOnePass(Request request) {
        String sqlRequest = "SELECT * FROM requests WHERE passenger_login = ?";
        try {
            conn = DriverManager.getConnection(url, properties);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
            preparedStatement.setString(1, request.getPassengerLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            HashMap<String,List<Integer>> listOfEqualsRequests = new HashMap<>();

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

    @Override
    public void update(Request obj) throws SQLException {

    }
}
