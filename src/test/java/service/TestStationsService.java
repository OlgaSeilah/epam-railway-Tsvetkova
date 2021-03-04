package service;

import dao.StationsDAO;
import entity.Station;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestStationsService extends TestService {
    StationsDAO stationsDAO = new StationsDAO();
    StationsService stationsService = new StationsService(stationsDAO);

    final String stationName = generateRandomString(10);

    Station testStation = new Station(stationName);

    @BeforeSuite
    public final Station generateStation() {
        testStation.setStationName(stationName);
        return testStation;
    }

    @Test
    public void getListOfStationNames() throws SQLException {
        Assert.assertNotNull(stationsDAO.readAllStationNames());
    }

    @Test
    public void createNewStationTest () {
        Assert.assertTrue(stationsService.addNewStation(testStation));
    }

    @Test
    public void deleteStationTest () {
        Assert.assertTrue(stationsService.deleteStation(testStation));
    }



















}
