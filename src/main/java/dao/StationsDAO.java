package dao;

import entity.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationsDAO extends ConnectionToDB  implements StationsDaoInterface{

    @Override
    public Station create(Station obj) throws SQLException {
        return null;
    }

    @Override
    public Station read(String stationName) throws SQLException {
        Station station = new Station();
        String sqlRequest = "select * from stations where station_name = ?";
        conn = DriverManager.getConnection(url, properties);
        PreparedStatement preparedStatement = conn.prepareStatement(sqlRequest);
        preparedStatement.setString(1, stationName);
        ResultSet resultSet = preparedStatement.executeQuery();
        String name = null;

        while (resultSet.next()) {
            name = resultSet.getString("station_name");
        }
        station.setStationName(name);
        return station;

    }

    @Override
    public void update(Station obj) throws SQLException {

    }

    @Override
    public void delete(Station obj) throws SQLException {

    }

    public List<String> readAllStationNames() throws SQLException { //TODO создать интерфейс с этим методом
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


}
