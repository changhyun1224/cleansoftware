import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import payroll.addEmployee.AddHourlyEmployee;
import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.TimeCard;
import payroll.timcard.TimeCardTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeCardTest {

    @Test
    public void TimeCardTest() throws Exception {
        int empId = 2;
        Calendar payDate = new GregorianCalendar(2001, 11, 31);

        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 8.0);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        Assertions.assertEquals(pc, hc);

        TimeCard tc = hc.getTimeCard(payDate);
        Assertions.assertNotNull(tc);
        Assertions.assertEquals(8.0, tc.getItsHours());
    }
}
