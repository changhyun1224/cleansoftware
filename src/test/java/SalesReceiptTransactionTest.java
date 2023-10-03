import org.junit.jupiter.api.*;
import payroll.addEmployee.AddCommissionedEmployee;
import payroll.classification.CommissionedClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.SalesReceipt;
import payroll.salesReceipt.SalesReceiptTransaction;

public class SalesReceiptTransactionTest {

    @Test
    public void salesReceiptTransaction() throws Exception {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill", "Home", 2500, 3);
        t.execute();

        SalesReceiptTransaction tct = new SalesReceiptTransaction(empId, 20011031, 3);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        Assertions.assertEquals(pc, cc);

        SalesReceipt sr = cc.getSalesReceipt(2);
        Assertions.assertNotNull(sr);
        Assertions.assertEquals(3, sr.getAmount());
    }

}
