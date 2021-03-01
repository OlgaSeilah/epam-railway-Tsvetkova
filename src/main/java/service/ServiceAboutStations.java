package service;
import dao.StationsDAO;
import entity.Station;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServiceAboutStations {
        StationsDAO stationsDAO = new StationsDAO();

    public List<String> getListOfStationNames() {
            try {
                return stationsDAO.readAllStationNames();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
    }

    public boolean addNewStation(Station newStation) {
        stationsDAO.create(newStation);
        return true;
    }

    public boolean deleteStation(Station deleteStation) {
            try {
                stationsDAO.delete(deleteStation);
                return true;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return false;
        }











}
