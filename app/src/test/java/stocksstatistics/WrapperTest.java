package stocksstatistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WrapperTest {

    @Test
    void isSaveDatabaseCreatingAFile() {
        //given;
        String directory = System.getProperty("user.dir")+"\\files\\";
        String fileName ="xStation5Test.csv";
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
        employee02.addOperationToOverallList(operation2);try {
            employeeDatabase.addEmployeeWithHistory(firstName,lastName,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeDatabase.getEmployeeWithLastName("Kowalski").calculateStatisticsFromHistory();

        employeeDatabase.addEmployeeWithOperations(employee02);
        employeeDatabase.getEmployeeWithLastName("Krawczyk").calculateStatisticsFromHistory();
        Wrapper wrapper = new Wrapper();
        //when
        wrapper.saveDatabase("TestDatabase",employeeDatabase);
        //then
        Assertions.assertTrue(new File(directory+"/TestDatabase.ser").isFile());
    }


}