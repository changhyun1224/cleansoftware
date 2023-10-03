import org.junit.jupiter.api.*;
import payroll.addEmployee.AddSalariedEmployee;
import payroll.classification.PaymentClassification;
import payroll.classification.SalariedClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.method.HoldMethod;
import payroll.method.PaymentMethod;
import payroll.schedule.MonthlySchedule;
import payroll.schedule.PaymentSchedule;

public class AddSalariedEmployeeTest {

    @Test
    public void addSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        SalariedClassification sc = (SalariedClassification) pc;
        Assertions.assertEquals(pc, sc);

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        Assertions.assertEquals(ps, ms);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        Assertions.assertEquals(pm, hm);
    }
}
