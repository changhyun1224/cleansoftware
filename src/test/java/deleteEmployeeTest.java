import org.junit.jupiter.api.*;
import payroll.addEmployee.AddCommissionedEmployee;
import payroll.database.PayrollDatabase;
import payroll.deleteEmployee.DeleteEmployeeTransaction;
import payroll.entity.Employee;

import java.util.Map;

public class deleteEmployeeTest {

    @Test
    public void deleteEmployeeTest() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();

        Map<Integer, Employee> data = PayrollDatabase.getAllEmployee();

        Employee e2 = PayrollDatabase.getEmployee(empId);
        Assertions.assertNull(e2);
    }
}
