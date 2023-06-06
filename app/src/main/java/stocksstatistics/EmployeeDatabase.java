package stocksstatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDatabase {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String COMMA_DELIMITER = ",";

    public void addEmployeeWithHistory(String firstName, String lastName, String fileName) throws IOException {
        Employee employee = new Employee(firstName, lastName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            String symbol = null;
            String type = null;
            String openTime = null;
            String closeTime = null;
            double profitOrLoss = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                if (values[0] != "Symbol") {
                    for (int i = 0; i < values.length; i++) {
                        switch (i) {
                            case 0://symbol
                                symbol = values[i];
                                break;
                            case 2://type
                                type = values[i];
                                break;
                            case 4://open
                                openTime = values[i];
                                break;
                            case 6://close
                                closeTime = values[i];
                                break;
                            case 8://net profit
                                profitOrLoss = Double.parseDouble(values[i]);
                                break;
                        }
                    }
                    employee.addOperationToList(new Operation(symbol, type, openTime, closeTime, profitOrLoss));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeList.add(employee);
    }




}
