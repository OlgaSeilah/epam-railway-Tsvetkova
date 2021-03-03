package service;

import dao.RequestDAO;
import dao.StationsDAO;
import dao.UserDAO;
import entity.Request;
import entity.Station;
import entity.User;

import java.util.Random;

public class TestService {
    RequestDAO requestDAO = new RequestDAO();
    StationsDAO stationsDAO = new StationsDAO();
    RequestService requestService = new RequestService(requestDAO, stationsDAO);
    StationsService stationsService = new StationsService(stationsDAO);
    String passengerLogin;
    String startStation;
    String destinationStation;
    Request request = new Request(passengerLogin, startStation, destinationStation);
    static String generateRandomString(int len) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        Random rnd = new Random();
        final StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < len; i++) {
            randomString.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        return randomString.toString();
    }

    UserDAO userDAO = new UserDAO();
    UserService userService = new UserService(userDAO);

    final String name = generateRandomString(10);
    final String surname = generateRandomString(5);
    final String login = generateRandomString(5);
    final String password = generateRandomString(10);
    final String stationName = generateRandomString(10);

    User testUser = new User(name, surname, login, password);
    Station testStation = new Station(stationName);

}
