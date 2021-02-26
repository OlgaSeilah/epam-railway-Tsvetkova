package dao;

import entity.Station;

import java.sql.*;

public class StationsDAO extends ConnectionToDB  implements StationsDaoInterface{

    public void listOfStations() throws SQLException {

//        try  {
//            conn = DriverManager.getConnection(url, properties);
//            Statement statement = conn.createStatement();
//            ResultSet result = statement.executeQuery("select * from stations");
//            while (result.next()) {
//                String dataBasePrintByColumnName = result.getString("station_name");
//                System.out.println(dataBasePrintByColumnName);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

    }


    @Override
    public Object create(Object obj) throws SQLException {
        return null;
    }

    @Override
    public Object read() throws SQLException {
        try  {
            conn = DriverManager.getConnection(url, properties);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select * from stations");
            while (result.next()) {
                String dataBasePrintByColumnName = result.getString("station_name");
                System.out.println(dataBasePrintByColumnName);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Object obj, Integer id) throws SQLException {

    }

    @Override
    public void delete(Object obj) throws SQLException {

    }
}
