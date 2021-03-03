package service;

import dao.RequestDAO;
import dao.StationsDAO;
import entity.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestRequestService {
    RequestDAO requestDAO = new RequestDAO();
    StationsDAO stationsDAO = new StationsDAO();
    RequestService requestService = new RequestService(requestDAO, stationsDAO);
    String passengerLogin;
    String startStation;
    String destinationStation;
    Request request = new Request(passengerLogin, startStation, destinationStation);


    @Test
    public void createRequestYTest() throws SuchStationDoesNotExistException, SQLException {
        passengerLogin = "admin";
        startStation = "Девяткино";
        destinationStation = "Мурино";
        request.setPassengerLogin(passengerLogin);
        request.setStartStation(startStation);
        request.setDestinationStation(destinationStation);
        System.out.println(request.toString());
        Assert.assertTrue(requestService.createRequest(request));
    }

    @Test
    public void deleteRequestTest() {
        passengerLogin = "admin";
        startStation = "Девяткино";
        destinationStation = "Мурино";
        Assert.assertTrue( requestService.deleteRequest(request));
    }



}
