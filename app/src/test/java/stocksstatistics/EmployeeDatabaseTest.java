package stocksstatistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
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
        Assertions.assertFalse(employeeDatabase.getEmployeeList().get(0).getOperationList().isEmpty());
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
        System.out.println(employeeDatabase.getEmployeeWithLastName("Kowalski").getStats().toString());
        Assertions.assertNotNull(employeeDatabase.getEmployeeWithLastName("Kowalski").getStats());
    }
}