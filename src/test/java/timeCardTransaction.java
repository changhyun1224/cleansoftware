import org.junit.jupiter.api.*;
import payroll.addEmployee.AddHourlyEmployee;
import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.TimeCard;
import payroll.timcard.TimeCardTransaction;

public class timeCardTransaction {

    @Test
    public void deleteEmployeeTest() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, 20011031, 8.0);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        Assertions.assertEquals(pc, hc);

        TimeCard tc = hc.getTimeCard(20011031);
        Assertions.assertNotNull(tc);
        Assertions.assertEquals(8.0, tc.getItsHours());
    }
}
