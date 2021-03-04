package service;

import dao.RequestDAO;
import dao.StationsDAO;
import entity.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestRequestService extends TestService {
    RequestDAO requestDAO = new RequestDAO();
    StationsDAO stationsDAO = new StationsDAO();
    RequestService requestService = new RequestService(requestDAO, stationsDAO);
    String passengerLogin;
    String startStation;
    String destinationStation;
    Request request = new Request(passengerLogin, startStation, destinationStation);


    @Test
    public void createRequestTest() throws SuchStationDoesNotExistException, SQLException {
        passengerLogin = "admin";
        startStation = "Девяткино";
        destinationStation = "Мурино";
        request.setPassengerLogin(passengerLogin);
        request.setStartStation(startStation);
        request.setDestinationStation(destinationStation);
        Assert.assertTrue(requestService.createRequest(request));
    }

    @Test
    public void deleteRequestTest() {
        passengerLogin = "admin";
        startStation = "Девяткино";
        destinationStation = "Мурино";
        Assert.assertTrue( requestService.deleteRequest(request));
    }

    @Test
    public void getThreeMostPopularStationsIsNotNullTest() {
        Assert.assertNotNull(requestService.getThreeMostPopularStations());
    }

    @Test
    public void getThreeMostPopularStationsHasSizeThreeTest() {
        Assert.assertEquals(requestService.getThreeMostPopularStations().size(), 3);
    }

    @Test
    public void getListOfRequestsInOneUserTest() {
        String login = "test";
        request.setPassengerLogin(login);
        HashMap<String, ArrayList<Integer>> test = requestService.getListOfRequestsInOneUser(request);
        ArrayList<Integer> req = new ArrayList<>();
        req.add(11);
        HashMap<String, List<Integer>> expected = new HashMap<>();
        expected.put(login, req);
        Assert.assertEquals(test, expected);
    }





}
