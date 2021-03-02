import dao.RequestDAO;
import dao.StationsDAO;
import dao.UserDAO;
import dao.exception.IncorrectPasswordException;
import dao.exception.UserDoesNotExistException;
import entity.Station;
import entity.User;
import service.*;
import java.util.Scanner;

public class MenuCashier {
    public void menuCashier(Scanner scanner) {
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        StationsDAO stationsDAO = new StationsDAO();
        StationsService stationsService = new StationsService();
        RequestDAO requestDAO = new RequestDAO();
        RequestService requestService = new RequestService(requestDAO, stationsDAO);

        User cashier = null;

        System.out.println("        Меню кассира");

        System.out.println("Список доступных функций:\n" +
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
                    System.out.print("Авторизация  ");
                    System.out.println("введите логин"); // admin
                    String loginAdmin = scanner.nextLine();
                    System.out.println("введите пароль"); // admin
                    String passwordAdmin = scanner.nextLine();
                    try {
                        cashier = userService.authorisation(loginAdmin, passwordAdmin);
                        System.out.println("Добро пожаловать, " + cashier.getName() + " " + cashier.getSurname() + ", вы успешно авторизованы.\n" +
                                "Нажмите \"4\" для оформления заявки"); //TODO тут что-то изменить?
                    } catch (UserDoesNotExistException e) {
                        System.out.println("не тот логин");
                    } catch (IncorrectPasswordException ex) {
                        System.out.println("не тот пароль");
                    }
                    break;
                    case "1":                                                         // ПРОСМОТР СПИСКА СТАНЦИЙ
                    System.out.println("Список станций в бд:");
                    stationsService.getListOfStationNames().forEach(System.out::println);
                    break;
                case "2":                                                      // Просмотреть список зарегистрированных пассажиров
                    System.out.println("Список зарегистрированных пользователей:");
                    System.out.println(userService.getListOfPassNames());
//                    TODO не разобралась, как вывести красиво
                    break;

                case "3":
                    if (cashier == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 0");
                    else {                                                                                  // Добавить станцию в бд
                        System.out.println("Введите название станции");
                        String stationName = scanner.nextLine();
                        Station newStation = new Station(stationName);
                        newStation.setStationName(stationName);
                        if (stationsService.addNewStation(newStation)) {
                            System.out.println("Станция " + stationName + " успешно добавлена");
                        } else {
                            System.out.println("не удалось добавить станцию");
                        }
                    }
                    break;
                case "4":
                    if (cashier == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 0");
                    else {                                                                      //удалить станцию из бд
                        System.out.println("Введите название станции, которую хотите удалить");
                        String delStationName = scanner.nextLine();                 //TODO переспросить ли админа на этот счет?
                        Station deleteStation = new Station();
                        deleteStation.setStationName(delStationName);
                        if (stationsService.deleteStation(deleteStation)) {
                            System.out.println("Станция " + delStationName + " успешно удалена");
                        } else {
                            System.out.println("не удалось удалить станцию");
                        }
                    }
                    break;

//                case "5":
//                    if (cashier == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 0");
//                    else {                                                                      //удалить станцию из бд
//                        System.out.println("Для удаления заявки");
//                        if ()) {
//                            System.out.println("Станция " + delStationName + " успешно удалена");
//                        } else {
//                            System.out.println("не удалось удалить станцию");
//                        }
//                    }
//
//                    break;

                case "6":
                    if (cashier == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 0");   // TODO ye;yf kb 'nf ghjdthrf

                    else {
                        System.out.println("Введите логин пассажира, которого хотите удалить");
                        String delPassName = scanner.nextLine();
                        User deleteUser = new User(delPassName);
                        if (userService.deleteUser(deleteUser)) {
                            System.out.println("Пассажир " + deleteUser.getLogin() + " успешно удален");
                        } else {
                            System.out.println("не удалось удалить пассажира");
                        }
                    }
                    break;
                case "5": // 3 наиболее посещаемые станции
                    requestService.getThreeMostPopularStations().forEach(System.out::println);

                case "7":
                    if (cashier == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 0");   // TODO

                    else {
                        System.out.println("Введите айди заявки, которую хотите удалить");
                        String delPassName = scanner.nextLine();
                        User deleteUser = new User(delPassName);
                        if (userService.deleteUser(deleteUser)) {
                            System.out.println("Пассажир " + deleteUser.getLogin() + " успешно удален");
                        } else {
                            System.out.println("не удалось удалить пассажира");
                        }
                    }
                    break;
                case "":






//
//                    }
//                case "5":
//                    current = null;
//                    System.out.println("Вы успешно вышли из профиля");
//
//                    break;
//                case "6":
//                    System.out.println("До свидания!");
//                    System.exit(0);
//                    break;
//
//                default:
//                    System.out.println("Вы запросили несуществующую функцию: " + choose);
//                    System.out.println("Работа программы завершена");
//                    System.exit(0);
//
//            }


                default:
                    throw new IllegalStateException("Unexpected value: " + choose);
            }
        }
    }
}