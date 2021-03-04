import dao.SetupDBScript;

import java.util.Scanner;


//TODO как бы вынести авторизацию куда-то?  1) пекеджи для исключений!!
public class RailwayMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("        RAILWAY BOOKING SYSTEM");
        System.out.println("Список доступных функций:\n" +
                "1 - Выбрать роль\n" +
                "2 - Выйти из системы\n" +
                "3 - Создать БД\n" +
                "Введите номер искомой функции:");

//        while (true) {
        String choose = scanner.nextLine();
        switch (choose) {
            case "1":
                System.out.println("Выберите роль:\n 1 - кассир\n 2 - пассажир");
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        System.out.println("Cashier");
                        MenuCashier menuCashier = new MenuCashier();
                        menuCashier.menuCashier(scanner);
                        break;
                    case "2":
                        System.out.println("Passenger");
                        MenuPassenger menuPassenger = new MenuPassenger();
                        menuPassenger.menuPass(scanner);
                        break;
                }
                break;
            case "2":
                System.exit(0);
            case "3":
                System.out.println("create db");
                SetupDBScript setup = new SetupDBScript();
                setup.setupDBScript();
                break;
            default:
                System.out.println("Повторите запуск программы");
                System.out.println("default");

        }
    }




}
