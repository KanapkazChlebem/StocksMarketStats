package stocksstatistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void calculateStatisticsFromHistory() {
        //given
        Employee employee = new Employee("Jan","Kowalski");
        Operation operation0 = new Operation("GBPUSD","Sell Limit", "12.10.2022 20:11:09",
                "03.11.2022 11:26:12", -182.38);
        Operation operation1 = new Operation("GBPUSD","Sell", "17.10.2022 07:43:58",
                "21.10.2022 10:49:10", 63.61);
        Operation operation2 = new Operation("USDPLN","Sell Limit", "12.10.2022 16:22:44",
                "13.10.2022 11:00:11", 34.22);
        employee.addOperationToOverallList(operation0);
        employee.addOperationToOverallList(operation1);
        employee.addOperationToOverallList(operation2);
        //when
        employee.calculateStatisticsFromHistory();
        //then
        System.out.println(employee.getOverallStats().toString());
        Assertions.assertNotNull(employee.getOverallStats());
    }
}