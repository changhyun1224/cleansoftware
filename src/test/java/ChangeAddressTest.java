import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import payroll.changeEmployee.ChangeAddressTransaction;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public class ChangeAddressTest {

    private int empId = 2;

    @Test
    public void testChangeAddressTransaction() throws Exception {
        String changeAddress = "Home2";

        ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, changeAddress);
        cat.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals(changeAddress, e.getAddress());
    }

}
