package dao;

import entity.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public Object read(Object someId) throws SQLException {
        return null;
    }

//    @Override
//    public Object read(Object obj) throws SQLException {
//        return null;
//    }

//    @Override
    public List<String> readAllStationNames() throws SQLException { //создать интерфейс с этим методом
        try  {
            conn = DriverManager.getConnection(url, properties);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select * from stations");
            List<String> names = new ArrayList<>();
            while (result.next()) {
                String stationName = result.getString("station_name");
                names.add(stationName);
            }
            return names; //TODO некоторый массив данных

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Object obj) throws SQLException {

    }

    @Override
    public void delete(Object obj) throws SQLException {

    }
}
