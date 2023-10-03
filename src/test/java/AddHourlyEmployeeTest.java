import org.junit.jupiter.api.*;
import payroll.addEmployee.AddHourlyEmployee;
import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.method.HoldMethod;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;
import payroll.schedule.WeeklySchedule;

public class AddHourlyEmployeeTest {

    @Test
    public void addSalariedEmployee() {
        int empId = 1;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        HourlyClassification sc = (HourlyClassification) pc;
        Assertions.assertEquals(pc, sc);

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ms = (WeeklySchedule) ps;
        Assertions.assertEquals(ps, ms);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        Assertions.assertEquals(pm, hm);
    }
}
