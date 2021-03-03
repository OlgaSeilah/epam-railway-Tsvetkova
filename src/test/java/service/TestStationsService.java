package service;

import entity.Station;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestStationsService extends TestService {

    @Test
    public void getListOfStationNames() throws SQLException {
        Assert.assertNotNull(stationsDAO.readAllStationNames());
    }

    @BeforeSuite
    public final Station generateStation() {
        testStation.setStationName(stationName);
        return testStation;
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
