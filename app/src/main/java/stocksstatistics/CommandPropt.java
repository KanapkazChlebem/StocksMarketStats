package stocksstatistics;

import java.io.IOException;
import java.util.Scanner;

public class CommandPropt {
    Wrapper wrapper = new Wrapper();
    EmployeeDatabase employeeDatabase = new EmployeeDatabase();
    private Scanner scanner = new Scanner(System.in);
    private String command = scanner.nextLine();
    public void commandLoop(){
        System.out.println("Wybierz co chesz zrobic:"+'\n'+
                "1 - Wczytaj istniejaca baze danych"+'\n'+
                "2 - Zapisz baze na ktorej pracujesz"+'\n'+
                "3 - Dodaj nowego pracownika z pliku csv"+'\n'+
                "4 - Znajdz najlepszego pracownika wzgledem kryteriow"+'\n'+
                "5 - Wyswietl statystyki pracownika"+'\n'+
                "6 - Wyjdz");
        switch (command){
            case "1":{
                System.out.println("Podaj nazwe bazy, lub zostaw puste:");
                command=scanner.nextLine();
                if (command.isBlank()) command=null;
                employeeDatabase = wrapper.createOrReadDatabase(command);
                System.out.println("Pomyslnie wczytano baze");
                commandLoop();
                break;
            }
            case "2":{
                System.out.println("Podaj nazwe bazy do zapisu, lub zostaw puste:");
                command=scanner.nextLine();
                if (command.isBlank()) command=null;
                wrapper.saveDatabase(command,employeeDatabase);
                System.out.println("Pomyslnie wczytano baze");
                commandLoop();
                break;

            }
            case "3":{
                System.out.println("Podaj imie pracownika");
                String imie = scanner.nextLine();
                System.out.println("Podaj nazwisko pracownika");
                String nazwisko = scanner.nextLine();
                System.out.println("Podaj nazwę pliku .csv (Powinien znajdowac sie w folderze app)");
                command = scanner.nextLine();
                try {
                    employeeDatabase.addEmployeeWithHistory(imie,nazwisko,command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Pomyslnie dodano pracownika");
                commandLoop();
                break;
            }
            case "4":{
                System.out.println("Podaj kryteria wzgledem pracownika:"+'\n'+
                        "1 - najwieksza sekwencja zyskow,"+'\n'+
                        "2 - srednia sekwencja zyskow,"+'\n'+
                        "3 - najwiekszy jednorazowy zysk,"+'\n'+
                        "4 - sredni zysk na transakcje,"+'\n'+
                        "4 - srednia zyskow,"+'\n'+
                        "5 - suma zyskow,"+'\n'+
                        "6 - procent zyskownych transakcji");
                command= scanner.nextLine();
                if (Integer.getInteger(command)<7&&Integer.getInteger(command)>0) {
                    Employee bestEmployee = employeeDatabase.findOverallBestEmployee(command);
                    System.out.println("Najlepszy pracownik pod wybranym katem to "+bestEmployee.getFirstName()+
                            " "+bestEmployee.getLastName()+'\n'+
                            "Jego statystyki: "+'\n'+ bestEmployee.getOverallStats().toString());
                    commandLoop();
                    break;
                } else
                    System.out.println("Bledny wybor, powrot do menu");
                commandLoop();
                break;
            }
            case "5":{
                System.out.println("Podaj nazwisko pracownika");
                command = scanner.nextLine();
                Employee employee =
                        employeeDatabase.getEmployeeWithLastName(command);
                if (employee == null) {
                    System.err.println("Podany pracownik nie istnieje w bazie");
                    commandLoop();
                    break;
                }
                System.out.println("Wybrany Pracownik to "+employee.getFirstName()+
                        " "+employee.getLastName()+'\n'+
                        "Jego statystyki: "+'\n'+ employee.getOverallStats().toString());
                commandLoop();
                break;
            }
            case "6":{ break;}
            default: {
                System.out.println("Bledny wybor, sprobuj jeszcze raz");
                commandLoop();
                break;
            }
        }
    }
}
