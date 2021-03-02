import java.util.Scanner;


//TODO как бы вынести авторизацию куда-то?
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
                        // тут вызвать кассира                    TODO переименовать его в админа, а таблицу в юзерс?
                        System.out.println("Cashier");
                        MenuCashier menuCashier = new MenuCashier();
                        menuCashier.menuCashier(scanner);
                        break;
                    case "2":
                        //тут вызвать пассажира
                        System.out.println("Passenger");
                        MenuPassenger menuPassenger = new MenuPassenger();
                        menuPassenger.menuPass(scanner);
                        break;
                }
                break;
            case "2":
                System.exit(0);
            default:
                System.out.println("default");

        }
    }




}
