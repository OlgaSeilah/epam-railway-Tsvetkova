import dao.RequestDAO;
import dao.StationsDAO;
import dao.UserDAO;
import entity.Request;
import entity.Station;
import entity.User;
import service.*;

import java.util.*;

public class MenuCashier {
    public void menuCashier(Scanner scanner) {
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        StationsDAO stationsDAO = new StationsDAO();
        StationsService stationsService = new StationsService();
        RequestDAO requestDAO = new RequestDAO();
        RequestService requestService = new RequestService(requestDAO, stationsDAO);

        User currentCashier = null;

        System.out.println("        Меню кассира \n".toUpperCase());
        System.out.println("Необходимо авторизоваться для продолжения работы\n");
        System.out.println("введите логин"); // admin
        String loginAdmin = scanner.nextLine();
        System.out.println("введите пароль"); // admin
        String passwordAdmin = scanner.nextLine();
        try {
            User cashier = userService.authorisation(loginAdmin, passwordAdmin);
            System.out.println("Добро пожаловать, " + cashier.getName() + " " + cashier.getSurname()
                    + ", вы успешно авторизованы.\n");
            currentCashier = cashier;
        } catch (UserDoesNotExistException e) {
            System.out.println("Вы ввели неверный логин");
        } catch (IncorrectPasswordException ex) {
            System.out.println("Вы ввели неверный пароль");
        }

        if (currentCashier != null) {
            System.out.println("Список доступных функций:\n" +
                    "0 - Просмотр списка заявок у пассажира\n" +
                    "1 - Просмотр списка доступных станций\n" +
                    "2 - Просмотреть список зарегистрированных пассажиров\n" +
                    "3 - Добавить станцию\n" +
                    "4 - Удалить станцию\n" +
                    "5 - Вывести три наиболее посещаемые станции\n" +
                    "6 - Удалить пассажира\n" +
                    "7 - Удалить заявку на билет\n" +
                    "8 - Завершить выполнение программы\n" +
                    "Введите номер искомой функции:");
            while (true) {
                String choose = scanner.nextLine();

                switch (choose) {

                    case "0":
                        System.out.println("Введите логин пассажира");
                        String passLogin = scanner.nextLine();
                        System.out.println("Список  заявок у  пассажира:");
                        Request request = new Request(passLogin);
                        HashMap<String, List<Integer>> listOfRequestsInOneUser = requestService.getListOfRequestsInOneUser(request);

                        for (HashMap.Entry<String, List<Integer>> pair : listOfRequestsInOneUser.entrySet())
                        {
                            String login = pair.getKey();
                            List<Integer> idOfRequest = pair.getValue();
                            System.out.println("Логин пассажира: " + login + "\nСписок id заявок: " + idOfRequest);
                            break;
                        }
                        break; //TODO не выходит в меню
                    case "1":
                        System.out.println("Список станций на линии:");
                        stationsService.getListOfStationNames().forEach(System.out::println);
                        break;

                    case "2":
                        System.out.println("Список зарегистрированных пользователей:");
                        Map<String,String> loginsAndNames = userService.getListOfPassNames();

                        for (Map.Entry<String, String> pair : loginsAndNames.entrySet())
                        {
                            String login = pair.getKey();
                            String name = pair.getValue();
                            System.out.println("Логин пассажира: " + login + " и " + "имя пассажира: " + name);
                        }


//                    TODO КАК перестать повторять?
                        break;
                    case "3":
                        System.out.println("Введите название станции");
                        String stationName = scanner.nextLine();
                        Station newStation = new Station(stationName);
                        newStation.setStationName(stationName);
                        if (stationsService.addNewStation(newStation)) {
                            System.out.println("Станция " + stationName + " успешно добавлена");
                        } else {
                            System.out.println("не удалось добавить станцию");
                        }
                        break;
                    case "4":
                        System.out.println("Введите название станции, которую хотите удалить");
                        String delStationName = scanner.nextLine();
                        Station deleteStation = new Station();
                        deleteStation.setStationName(delStationName);
                        if (stationsService.deleteStation(deleteStation)) {
                            System.out.println("Станция " + delStationName + " успешно удалена");
                        } else {
                            System.out.println("не удалось удалить станцию");
                        }
                        break;
                    case "5":
                        System.out.println("Три наиболее посещаемые станции:");
                        requestService.getThreeMostPopularStations().forEach(System.out::println);
                        break;
                    case "6":
                        System.out.println("Введите логин пассажира, которого хотите удалить");
                        String delPassName = scanner.nextLine();
                        User deleteUser = new User(delPassName);
                        if (userService.deleteUser(deleteUser)) {
                            System.out.println("Пассажир " + deleteUser.getLogin() + " успешно удален");
                        } else {
                            System.out.println("не удалось удалить пассажира");
                        }
                        break;

                    case "7":
                        System.out.println("Введите данные заявки, которую хотите удалить");
                        System.out.println("Введите логин пассажира");
                        String passLoginForRequestDel = scanner.nextLine();
                        System.out.println("Введите станцию отправления");
                        String delStartStationName = scanner.nextLine();
                        System.out.println("Введите станцию назначения");
                        String delDestStationName = scanner.nextLine();
                        Request delRequest = new Request(passLoginForRequestDel, delStartStationName, delDestStationName);
                        if (requestService.deleteRequest(delRequest)) {


                            System.out.println("Заявка пассажира " + delRequest.getPassengerLogin() + " успешно удалена");
                        } else {
                            System.out.println("не удалось удалить пассажира");
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + choose);
                }
            }
        }
    }
}