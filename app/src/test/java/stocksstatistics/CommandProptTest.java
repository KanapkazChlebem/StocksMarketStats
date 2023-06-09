package stocksstatistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProptTest {

    @Test
    void commandLoop() {
        //given
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        Wrapper wrapper = new Wrapper();
        CommandPropt commandPropt = new CommandPropt();
        //when
        commandPropt.commandLoop();
        //then
        Assertions.assertNotNull(employeeDatabase.getEmployeeList());
    }
}