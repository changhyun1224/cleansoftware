import org.junit.jupiter.api.*;
import payroll.addEmployee.AddHourlyEmployee;
import payroll.changeEmployee.ChangeNameTransaction;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public class ChangeNameTest {

    @Test
    public void changeNameTest() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob");
        cnt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals("Bob", e.getName());
    }
}
