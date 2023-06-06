package stocksstatistics;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private EmployeeStatistics stats;
    private List<Operation> operationList = new ArrayList<>();
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void addOperationToList(Operation operation){
        operationList.add(operation);
    }

    public List<Operation> getOperationList() {
        return operationList;
    }
}
