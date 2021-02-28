import service.ServiceAboutStations;
import service.ServiceAboutUsers;

import java.util.Scanner;

public class RailwayMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("        RAILWAY BOOKING SYSTEM");
        System.out.println("Список доступных функций:\n" +
                "1 - Выбрать роль\n" +
                "2 - Выйти из системы\n" +
                "Введите номер искомой функции:");

//        while (true) {
        String choose = scanner.nextLine();
        switch (choose) {
            case "1":
                System.out.println("Выберите роль:\n 1 - кассир\n 2 - пассажир");
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        // тут вызвать кассира
                        System.out.println("Kassier");
                        break;
                    case "2":
                        //тут вызвать пассажира
                        System.out.println("Passenger");
                        MenuPassenger menuPassenger = new MenuPassenger();
                        menuPassenger.menu();
                        break;
                }
                break;
            case "2":
                System.exit(0);
//            case "3":
//                System.out.println("Here in 3");
//                ServiceAboutStations serviceAboutStations = new ServiceAboutStations();
//                serviceAboutStations.getListOfStations();
//                break;
            default:
                System.out.println("default");

        }
    }
}
