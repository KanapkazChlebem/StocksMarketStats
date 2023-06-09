package stocksstatistics;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase implements Serializable {

    private String directory = System.getProperty("user.dir")+"\\";

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String DELIMITER = ";";

    public void addEmployeeWithOperations(Employee employee){
        if (employee==null) throw new NullPointerException();
        else employeeList.add(employee);
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

    public Employee getEmployeeWithLastName(String lastname){
        if (lastname==null) return null;
        for (Employee employee: employeeList ){
            if (employee.getLastName().equals(lastname)) return employee;
        }
        System.err.println("Employee with "+lastname+" lastname not found!");
        return null;
    }




}
