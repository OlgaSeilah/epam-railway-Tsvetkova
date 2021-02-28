package service;
import dao.StationsDAO;
import entity.Station;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

    public class ServiceAboutStations {


    public List<String> getListOfStationNames() {
            StationsDAO stationsDAO = new StationsDAO();
            try {
                return stationsDAO.readAllStationNames();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
    }









}
