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
                    "1 - Просмотр списка заявок у пассажира\n" +
                    "2 - Просмотр списка доступных станций\n" +
                    "3 - Просмотреть список зарегистрированных пассажиров\n" +
                    "4 - Добавить станцию\n" +
                    "5 - Удалить станцию\n" +
                    "6 - Вывести три наиболее посещаемые станции\n" +
                    "7 - Удалить пассажира\n" +
                    "8 - Редактирование заявки\n" +
                    "9 - Удалить заявку на билет\n" +
                    "10 - Завершить выполнение программы\n" +
                    "Введите номер искомой функции:");
            while (true) {
                String choose = scanner.nextLine();

                switch (choose) {

                    case "1":
                        System.out.println("Введите логин пассажира");
                        String passLogin = scanner.nextLine();
                        System.out.println("Список  заявок у  пассажира:");
                        Request request = new Request(passLogin);
                        HashMap<String, ArrayList<Integer>> listOfRequestsInOneUser = requestService.getListOfRequestsInOneUser(request);

                        for (HashMap.Entry<String, ArrayList<Integer>> pair : listOfRequestsInOneUser.entrySet())
                        {
                            String login = pair.getKey();
                            ArrayList<Integer> idOfRequest = pair.getValue();
                            System.out.println("Логин пассажира: " + login + "\nСписок id заявок: " + idOfRequest);
                            break;
                        }
                        break;
                    case "2":
                        System.out.println("Список станций на линии:");
                        stationsService.getListOfStationNames().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Список зарегистрированных пассажиров:");
                        Map<String,String> loginsAndNames = userService.getListOfPassNames();

                        for (Map.Entry<String, String> pair : loginsAndNames.entrySet())
                        {
                            String login = pair.getKey();
                            String name = pair.getValue();
                            System.out.println("Логин пассажира: " + login + " и " + "имя пассажира: " + name);
                        }
                        break;
                    case "4":
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
                    case "5":
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
                    case "6":
                        System.out.println("Три наиболее посещаемые станции:");
                        requestService.getThreeMostPopularStations().forEach(System.out::println);
                        break;
                    case "7":
                        System.out.println("Введите логин пассажира, которого хотите удалить");
                        String delPassName = scanner.nextLine();
                        User deleteUser = new User(delPassName);
                        if (userService.deleteUser(deleteUser)) {
                            System.out.println("Пассажир " + deleteUser.getLogin() + " успешно удален");
                        } else {
                            System.out.println("не удалось удалить пассажира");
                        }
                        break;
                    case "8":
                        System.out.println("Введите логин пассажира, чью заявку хотите отредактировать");
                        String passLoginForRequestEdit = scanner.nextLine();
                        System.out.println("Список  заявок у  пассажира:");
                        Request requestForChoose = new Request(passLoginForRequestEdit);
                        HashMap<String, ArrayList<Integer>> listOfRequestsInOneUserForChoose
                                = requestService.getListOfRequestsInOneUser(requestForChoose);

                        for (HashMap.Entry<String, ArrayList<Integer>> pair : listOfRequestsInOneUserForChoose.entrySet())
                        {
                            String login = pair.getKey();
                            ArrayList<Integer> idOfRequest = pair.getValue();
                            System.out.println("Логин пассажира: " + login + "\nСписок id заявок: " + idOfRequest);
                            break;
                        }

                        System.out.println("Введите id заявки для редактирования");
                        int requestId = scanner.nextInt();

                        Request requestForEdit = requestService.read(requestId);
                        String startStationInReq = requestForEdit.getStartStation();
                        String destStationInReq = requestForEdit.getDestinationStation();
                        System.out.println("В заявке номер " + requestId + " стартовая станция: " +
                                startStationInReq + "\nстанция назначения: " + destStationInReq);

                        System.out.println("Введите новые параметры:\nСтартовая станция");
                        String startStationForEdit = scanner.nextLine();
                        System.out.println(startStationForEdit);

                        System.out.println("Станция назначения");
                        String destStationForEdit = scanner.nextLine();
                        System.out.println(destStationForEdit);

                        Request reqEdited = new Request(requestId, passLoginForRequestEdit, startStationForEdit, destStationForEdit);

                        System.out.println(reqEdited.toString());

                        reqEdited.setStartStation(startStationForEdit);
                        reqEdited.setDestinationStation(destStationForEdit);
                        reqEdited.setRequestId(requestId);

                        System.out.println(reqEdited.toString());

                        if (requestService.changeRequest(reqEdited)) {
                            System.out.println("Заявка пассажира " + passLoginForRequestEdit +
                                    " успешно изменена");
                        } else {
                            System.out.println("не удалось изменить заявку");
                        }
                        break;
                    case "9":
                        System.out.println("Введите данные заявки, которую хотите удалить");
                        System.out.println("Введите логин пассажира");
                        String passLoginForRequestDel = scanner.nextLine();
                        System.out.println("Введите станцию отправления");
                        String delStartStationName = scanner.nextLine();
                        System.out.println("Введите станцию назначения");
                        String delDestStationName = scanner.nextLine();
                        Request delRequest = new Request(passLoginForRequestDel, delStartStationName,
                                delDestStationName);
                        if (requestService.deleteRequest(delRequest)) {
                            System.out.println("Заявка пассажира " + delRequest.getPassengerLogin() +
                                    " успешно удалена");
                        } else {
                            System.out.println("не удалось удалить пассажира");
                        }
                        break;
                    case "10":
                        System.out.println("До свидания!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Вы запросили несуществующую функцию: " + choose +
                                "\n Работа программы завершена");
                        System.exit(0);                }
            }
        }
    }
}