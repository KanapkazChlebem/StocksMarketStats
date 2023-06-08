package stocksstatistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void calculateStatisticsFromHistory() {
        //given
        Employee employee = new Employee("Jan","Kowalski");
        Operation operation0 = new Operation("GBPUSD","Sell Limit", "12.10.2022 20:11",
                "03.11.2022 11:26", -182.38);
        Operation operation1 = new Operation("GBPUSD","Sell", "17.10.2022 07:43",
                "21.10.2022 10:49", 63.61);
        Operation operation2 = new Operation("USDPLN","Sell Limit", "12.10.2022 16:22",
                "13.10.2022 11:00", 34.22);
        employee.addOperationToList(operation0);
        employee.addOperationToList(operation1);
        employee.addOperationToList(operation2);
        //when
        employee.calculateStatisticsFromHistory();
        //then
        System.out.println(employee.getStats().toString());
        Assertions.assertNotNull(employee.getStats());
    }
}