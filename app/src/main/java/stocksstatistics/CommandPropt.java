package stocksstatistics;

import java.io.IOException;
import java.util.Scanner;

public class CommandPropt {
    Wrapper wrapper = new Wrapper();
    EmployeeDatabase employeeDatabase = new EmployeeDatabase();
    private Scanner scanner = new Scanner(System.in);
    private String command = null;

    public void commandLoop() {
        String response = null;
        System.out.println("Wybierz co chesz zrobic:" + '\n' +
                "1 - Wczytaj istniejaca baze danych" + '\n' +
                "2 - Zapisz baze na ktorej pracujesz" + '\n' +
                "3 - Dodaj nowego pracownika z pliku csv" + '\n' +
                "4 - Usuń pracownika" + '\n' +
                "5 - Znajdz najlepszego pracownika wzgledem kryteriow" + '\n' +
                "6 - Wyswietl statystyki pracownika" + '\n' +
                "7 - Wyswietl listę pracowników" + '\n' +
                "8 - Wyjdz");
        command = scanner.nextLine();
        switch (command) {
            case "1": {
                System.out.println("Podaj nazwe bazy, lub zostaw puste:");
                response = scanner.nextLine();
                if (response.isBlank()) response = null;
                employeeDatabase = wrapper.createOrReadDatabase(response);
                System.out.println("Pomyslnie wczytano baze");
                commandLoop();
                break;
            }
            case "2": {
                System.out.println("Podaj nazwe bazy do zapisu, lub zostaw puste:");
                response = scanner.nextLine();
                if (response.isBlank()) response = null;
                wrapper.saveDatabase(response, employeeDatabase);
                System.out.println("Pomyslnie zapisano baze");
                commandLoop();
                break;

            }
            case "3": {
                System.out.println("Podaj imie pracownika");
                String imie = scanner.nextLine();
                System.out.println("Podaj nazwisko pracownika");
                String nazwisko = scanner.nextLine();
                System.out.println("Podaj nazwę pliku .csv");
                response = scanner.nextLine();
                try {
                    if (employeeDatabase.addEmployeeWithHistory(imie, nazwisko, response) == null) {
                        System.err.println("Bledna nazwa pliku/ plik nie istnieje!");
                        commandLoop();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Pomyslnie dodano pracownika");
                commandLoop();
                break;
            }
            case "4": {
                System.out.println("Podaj nazwisko pracownika do usuniecia");
                response = scanner.nextLine();
                if (employeeDatabase.removeEmployee(response) == true) {
                    System.out.println("Pracownik o nazwisku " + response + " został usunięty!");
                    commandLoop();
                    break;
                } else System.out.println("Nie znaleziono pracownika o nazwisku " + response);
                commandLoop();
                break;
            }
            case "5": {
                System.out.println("Podaj kryteria wzgledem pracownika:" + '\n' +
                        "1 - najwieksza sekwencja zyskow," + '\n' +
                        "2 - srednia sekwencja zyskow," + '\n' +
                        "3 - najwiekszy jednorazowy zysk," + '\n' +
                        "4 - sredni zysk na transakcje," + '\n' +
                        "4 - srednia zyskow," + '\n' +
                        "5 - suma zyskow," + '\n' +
                        "6 - procent zyskownych transakcji");
                response = scanner.nextLine();
                if (employeeDatabase.findOverallBestEmployee(response) != null) {
                    Employee bestEmployee = employeeDatabase.findOverallBestEmployee(response);
                    System.out.println("Najlepszy pracownik pod wybranym katem to " + bestEmployee.getFirstName() +
                            " " + bestEmployee.getLastName() + '\n' +
                            "Jego statystyki: " + '\n');
                    employeeDatabase.printOverallEmployeeStats(bestEmployee);
                    commandLoop();
                    break;
                } else
                    System.out.println("Bledny wybor lub baza pusta, powrot do menu");
                commandLoop();
                break;
            }
            case "6": {
                System.out.println("Podaj nazwisko pracownika");
                response = scanner.nextLine();
                Employee employee =
                        employeeDatabase.getEmployeeWithLastName(response);
                if (employee == null) {
                    System.err.println("Podany pracownik nie istnieje w bazie");
                    commandLoop();
                    break;
                }
                System.out.println("Czy chcesz statystyki miesieczne czy ogolne?" + '\n' +
                        "1 - Miesieczne" + '\n' +
                        "2 - Ogolne" + '\n' +
                        "Inny klawisz - powrot");
                response = scanner.nextLine();
                switch (response) {
                    case "1": {
                        if (employee.getLastMonthStats() == null) {
                            System.err.println("Ten pracownik nie ma operacji z ostatniego miesiaca!");
                            commandLoop();
                            break;
                        }
                        System.out.println("Wybrany Pracownik to " + employee.getFirstName() +
                                " " + employee.getLastName() + '\n' +
                                "Jego statystyki: " + '\n');
                        employeeDatabase.printMonthEmployeeStats(employee);
                    }
                    case "2": {
                        System.out.println("Wybrany Pracownik to " + employee.getFirstName() +
                                " " + employee.getLastName() + '\n' +
                                "Jego statystyki: " + '\n');
                        employeeDatabase.printOverallEmployeeStats(employee);
                    }
                    default: {
                    }
                    commandLoop();
                    break;
                }

                commandLoop();
                break;
            }
            case "7": {
                if (employeeDatabase == null) {
                    System.out.println("Baza danych pusta! Wczytaj najpier bazę lub dodaj pracownika");
                    commandLoop();
                    break;
                }
                employeeDatabase.listAllEmployees();
                commandLoop();
                break;
            }
            case "8": {
                System.err.println("Czy chcesz zapisać baze danych na której pracowałeś?" + '\n' +
                        "1 - Tak" + '\n' +
                        "Dowolny klawisz - Nie, wyjdź z programu");
                response = scanner.nextLine();
                if (response == "1") {
                    wrapper.saveDatabase(employeeDatabase.getFileName(), employeeDatabase);
                    break;
                }
                break;
            }
            default: {
                System.out.println("Bledny wybor, sprobuj jeszcze raz");
                commandLoop();
                break;
            }
        }
    }

    public CommandPropt() {
    }
}
