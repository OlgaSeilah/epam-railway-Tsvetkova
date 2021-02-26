package service;
import com.sun.source.doctree.SerialDataTree;
import dao.StationsDAO;
import entity.Station;
import entity.User;

import java.sql.SQLException;
import java.util.Scanner;

    public class ServiceAboutStations {
        public static void operationsWithStations (Scanner scan){
        System.out.println("что здесь?");

    }

    public void getListOfStations () {
            System.out.println("Список станций в бд:");
            StationsDAO stationsDAO = new StationsDAO();
            try {
                stationsDAO.read();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
}







}
