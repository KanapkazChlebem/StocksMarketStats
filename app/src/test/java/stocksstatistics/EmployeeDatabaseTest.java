package stocksstatistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeDatabaseTest {

    @Test
    void isaddEmployeeWithHistoryProvidingEmployeeWithOperationList() {
        //given
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory+"/xStation5Test.csv";
        String firstName = "Jan";
        String lastName = "Kowalski";
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        //when
        try {
            employeeDatabase.addEmployeeWithHistory(firstName,lastName,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //then
        Assertions.assertFalse(employeeDatabase.getEmployeeList().isEmpty());
        Assertions.assertFalse(employeeDatabase.getEmployeeList().get(0).getOverallOperationList().isEmpty());
    }

    @Test
    void isEmployeeStatsSuccessfullyCreatedWithStats(){
        //given
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory+"/xStation5Test.csv";
        String firstName = "Jan";
        String lastName = "Kowalski";
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        //when
        try {
            employeeDatabase.addEmployeeWithHistory(firstName,lastName,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeDatabase.getEmployeeWithLastName("Kowalski").calculateStatisticsFromHistory();
        //then
        System.out.println(employeeDatabase.getEmployeeWithLastName("Kowalski").getOverallStats().toString());
        Assertions.assertNotNull(employeeDatabase.getEmployeeWithLastName("Kowalski").getOverallStats());


    }

    @Test
    void isFindOverallBestEmployeeReturningBestEmployee() {
        //given
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory+"/xStation5Test.csv";
        String firstName = "Jan";
        String lastName = "Kowalski";
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();

        Employee employee02 = new Employee("Karol","Krawczyk");
        Operation operation0 = new Operation("GBPUSD","Sell Limit", "12.10.2022 20:11:09",
                "03.11.2022 11:26:12", -182.38);
        Operation operation1 = new Operation("GBPUSD","Sell", "17.10.2022 07:43:58",
                "21.10.2022 10:49:10", 63.61);
        Operation operation2 = new Operation("USDPLN","Sell Limit", "12.10.2022 16:22:44",
                "13.10.2022 11:00:11", 34.22);
        employee02.addOperationToOverallList(operation0);
        employee02.addOperationToOverallList(operation1);
        employee02.addOperationToOverallList(operation2);
        //when
        try {
            employeeDatabase.addEmployeeWithHistory(firstName,lastName,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeDatabase.getEmployeeWithLastName("Kowalski").calculateStatisticsFromHistory();

        employeeDatabase.addEmployeeWithOperations(employee02);
        employeeDatabase.getEmployeeWithLastName("Krawczyk").calculateStatisticsFromHistory();
        //then
        Assertions.assertEquals(employee02,employeeDatabase.findOverallBestEmployee("totalProfit"));//Kowalski -802; Krawczyk -84
    }
}