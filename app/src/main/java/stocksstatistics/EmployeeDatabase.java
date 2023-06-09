package stocksstatistics;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeDatabase implements Serializable {

    private String fileName = "DefaultDatabase.ser";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String directory = System.getProperty("user.dir")+"\\files\\";

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String DELIMITER = ";";

    public void addEmployeeWithOperations(Employee employee){
        if (employee==null) throw new NullPointerException();
        else employeeList.add(employee);
    }

    public void listAllEmployees(){
        AsciiTable at = new AsciiTable();
        at.setTextAlignment(TextAlignment.LEFT);
        at.addRule();
        at.addRow(new Object[]{"Imię", "Nazwisko"});
        at.addRule();
        Iterator var2 = this.employeeList.iterator();
        while(var2.hasNext()) {
            Employee employee = (Employee) var2.next();
            at.addRow(new Object[]{employee.getFirstName(), employee.getLastName()});
        }
        at.addRule();
        at.getRenderer().setCWC((new CWC_FixedWidth()).add(20).add(20));
        System.out.println(at.render());
    }


    public Employee addEmployeeWithHistory(String firstName, String lastName, String fileName) throws IOException {
        if (fileName==null) return null;
        if (!fileName.endsWith(".csv")) fileName=fileName+ ".csv";
        fileName=directory+fileName;
        if (!Files.exists(Path.of(fileName))) return null;
        Employee employee = new Employee(firstName, lastName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            String symbol = null;
            String type = null;
            String openTime = null;
            String closeTime = null;
            double profitOrLoss = 0;
            String firstLineOfFile = "Symbol";
            Operation operation = new Operation();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                if (!values[0].equals(firstLineOfFile)) {
                    for (int i = 0; i < values.length; i++) {
                        switch (i) {
                            case 0://symbol
                                symbol = values[i];
                                break;
                            case 2://type
                                type = values[i];
                                break;
                            case 4://open
                                openTime = values[i];//.replace("  "," ");
                                break;
                            case 6://close
                                closeTime = values[i];//.replace("  "," ");
                                break;
                            case 8://net profit
                                profitOrLoss = Double.parseDouble(values[i]);
                                break;
                        }
                    }
                    operation =new Operation(symbol, type, openTime, closeTime, profitOrLoss);
                    employee.addOperationToOverallList(operation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        employee.calculateStatisticsFromHistory();
        employeeList.add(employee);
        return employee;
    }

    public Employee findLastMonthBestEmployee(String criteria){
        Employee bestEmployee = null;
        switch (criteria){
            case "maxSequenceProfits":{
                for (Employee employee: employeeList){
                    if (bestEmployee==null) bestEmployee=employee;
                    else if (bestEmployee.getLastMonthStats().getMaxSequenceProfits()<
                            employee.getLastMonthStats().getMaxSequenceProfits()) bestEmployee=employee;
                }
                break;
            }
            case "avgSequenceProfits":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getLastMonthStats().getAvgSequenceProfits()<
                        employee.getLastMonthStats().getAvgSequenceProfits()) bestEmployee=employee;
            }
                break;}
            case "maxProfitTransactions":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getLastMonthStats().getMaxProfitTransactions()<
                        employee.getLastMonthStats().getMaxProfitTransactions()) bestEmployee=employee;
            }
                break;}
            case "avgProfitTransactions":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getLastMonthStats().getAvgProfitTransactions()<
                        employee.getLastMonthStats().getAvgProfitTransactions()) bestEmployee=employee;
            }
                break;}
            case "totalProfit":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getLastMonthStats().getTotalProfitOrLoss()<
                        employee.getLastMonthStats().getTotalProfitOrLoss()) bestEmployee=employee;
            }
                break;}
            case "totalTransactions":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getLastMonthStats().getTotalTransactions()<
                        employee.getLastMonthStats().getTotalTransactions()) bestEmployee=employee;
            }
                break;}
            case "percentProfitTransactions":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getLastMonthStats().getPercentProfitTransactions()<
                        employee.getLastMonthStats().getPercentProfitTransactions()) bestEmployee=employee;
            }
                break;}
            default : return null;

        }
        return bestEmployee;
    }

    public Employee findOverallBestEmployee(String criteria){
        Employee bestEmployee = null;
        switch (criteria){
            case "1":{
                for (Employee employee: employeeList){
                    if (bestEmployee==null) bestEmployee=employee;
                    else if (bestEmployee.getOverallStats().getMaxSequenceProfits()<
                    employee.getOverallStats().getMaxSequenceProfits()) bestEmployee=employee;
                }
                break;
            }
            case "2":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getOverallStats().getAvgSequenceProfits()<
                        employee.getOverallStats().getAvgSequenceProfits()) bestEmployee=employee;
            }
                break;}
            case "3":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getOverallStats().getMaxProfitTransactions()<
                        employee.getOverallStats().getMaxProfitTransactions()) bestEmployee=employee;
            }
                break;}
            case "4":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getOverallStats().getAvgProfitTransactions()<
                        employee.getOverallStats().getAvgProfitTransactions()) bestEmployee=employee;
            }
                break;}
            case "5":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getOverallStats().getTotalProfitOrLoss()<
                        employee.getOverallStats().getTotalProfitOrLoss()) bestEmployee=employee;
            }
                break;}
            case "6":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getOverallStats().getTotalTransactions()<
                        employee.getOverallStats().getTotalTransactions()) bestEmployee=employee;
            }
                break;}
            case "7":{for (Employee employee: employeeList){
                if (bestEmployee==null) bestEmployee=employee;
                else if (bestEmployee.getOverallStats().getPercentProfitTransactions()<
                        employee.getOverallStats().getPercentProfitTransactions()) bestEmployee=employee;
            }
                break;}
            default : return null;

        }
        return bestEmployee;
    }

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    public void printOverallEmployeeStats(Employee employee){
        AsciiTable at = new AsciiTable();
        at.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        at.addRow(new Object[]{"Imię", "Nazwisko", "Preferowane transakcje",
        "Ilosc transakcji","Najdluzsza zyskowna transakcja", "Sredni czas zyskownych transakcji",
        "Najdluzsza stratna transakcja","Sreni czas stratnych transakcji","Najwieksza sekwencja zyskow",
        "Srednia sekwencja zyskow","Najwieksza sekwencja strat","Srednia sekwencja strat",
        "Najwiekszy zysk","Srednia zyskow","Najwieksza strata","Srednia strat","Suma transakcji","Transakcje \"zerowe\"",
        "Procent zyskow","Procent zyskow BUY","Procent zyskow SELL","Suma zyskow i strat","Bilans na dzien od zatrudnienia"});
        at.addRule();
            at.addRow(new Object[]{employee.getFirstName(), employee.getLastName(),employee.getOverallStats().getFavSymbol(),
                    employee.getOverallStats().getFavSymbolCounter(),employee.getOverallStats().getMaxHoldProfit().getDays()+" Dni",
                    employee.getOverallStats().getAvgHoldProfit().getDays()+" Dni", employee.getOverallStats().getMaxHoldLoss().getDays()+" Dni",
                    employee.getOverallStats().getAvgHoldLoss().getDays()+" Dni",employee.getOverallStats().getMaxSequenceProfits(),
                    decfor.format(employee.getOverallStats().getAvgSequenceProfits()),employee.getOverallStats().getMaxSequenceLoss(),
                    decfor.format(employee.getOverallStats().getAvgSequenceLoss()),employee.getOverallStats().getMaxProfitTransactions(),
                    decfor.format(employee.getOverallStats().getAvgProfitTransactions()),employee.getOverallStats().getMaxLossTransactions(),
                    decfor.format(employee.getOverallStats().getAvgLossTransactions()),employee.getOverallStats().getTotalTransactions(),
                    employee.getOverallStats().getLostLessTransactions(),decfor.format(employee.getOverallStats().getPercentProfitTransactions() * 100) + "%",
                    decfor.format(employee.getOverallStats().getPercentBuyProfit() * 100) + "%",
                    decfor.format(employee.getOverallStats().getPercentSellProfit() * 100) + "%",
                    employee.getOverallStats().getTotalProfitOrLoss(),decfor.format(employee.getOverallStats().getAvgProfitOrLossOverTime())+" na dzien"});
        at.addRule();
        at.getRenderer().setCWC((new CWC_FixedWidth()).add(20).add(20).add(20).add(20).add(30)
                .add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20)
                .add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20));
        System.out.println(at.render());


    }
    public boolean printMonthEmployeeStats(Employee employee){
        if (employee.getLastMonthStats()==null) return false;
        AsciiTable at = new AsciiTable();
        at.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        at.addRow(new Object[]{"Imię", "Nazwisko", "Preferowane transakcje",
                "Ilosc transakcji","Najdluzsza zyskowna transakcja", "Sredni czas zyskownych transakcji",
                "Najdluzsza stratna transakcja","Sreni czas stratnych transakcji","Najwieksza sekwencja zyskow",
                "Srednia sekwencja zyskow","Najwieksza sekwencja strat","Srednia sekwencja strat",
                "Najwiekszy zysk","Srednia zyskow","Najwieksza strata","Srednia strat","Suma transakcji","Transakcje \"zerowe\"",
                "Procent zyskow","Procent zyskow BUY","Procent zyskow SELL","Suma zyskow i strat","Bilans na dzien od zatrudnienia"});
        at.addRule();
        at.addRow(new Object[]{employee.getFirstName(), employee.getLastName(),employee.getLastMonthStats().getFavSymbol(),
                employee.getLastMonthStats().getFavSymbolCounter(),employee.getLastMonthStats().getMaxHoldProfit().getDays()+" Dni",
                employee.getLastMonthStats().getAvgHoldProfit().getDays()+" Dni", employee.getLastMonthStats().getMaxHoldLoss().getDays()+" Dni",
                employee.getLastMonthStats().getAvgHoldLoss().getDays()+" Dni",employee.getLastMonthStats().getMaxSequenceProfits(),
                decfor.format(employee.getLastMonthStats().getAvgSequenceProfits()),employee.getLastMonthStats().getMaxSequenceLoss(),
                decfor.format(employee.getLastMonthStats().getAvgSequenceLoss()),employee.getLastMonthStats().getMaxProfitTransactions(),
                decfor.format(employee.getLastMonthStats().getAvgProfitTransactions()),employee.getLastMonthStats().getMaxLossTransactions(),
                decfor.format(employee.getLastMonthStats().getAvgLossTransactions()),employee.getLastMonthStats().getTotalTransactions(),
                employee.getLastMonthStats().getLostLessTransactions(),decfor.format(employee.getLastMonthStats().getPercentProfitTransactions() * 100) + "%",
                decfor.format(employee.getLastMonthStats().getPercentBuyProfit() * 100) + "%",
                decfor.format(employee.getLastMonthStats().getPercentSellProfit() * 100) + "%",
                employee.getLastMonthStats().getTotalProfitOrLoss(),decfor.format(employee.getLastMonthStats().getAvgProfitOrLossOverTime())+" na dzien"});
        at.addRule();
        at.getRenderer().setCWC((new CWC_FixedWidth()).add(20).add(20).add(20).add(20).add(30)
                .add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20)
                .add(20).add(20).add(20).add(20).add(20).add(20).add(20).add(20));
        System.out.println(at.render());
        return true;

    }


    public boolean removeEmployee(String lastName){
        if (lastName==null) return false;
        for (Employee employee:employeeList){
            if (employee.getLastName().equals(lastName)){ employeeList.remove(employee);
            return true;
            }
        }
        return false;
    }

    public Employee getEmployeeWithLastName(String lastname){
        if (lastname==null) return null;
        for (Employee employee: employeeList ){
            if (employee.getLastName().equals(lastname)) return employee;
        }
        System.err.println("Employee with "+lastname+" lastname not found!");
        return null;
    }




}
