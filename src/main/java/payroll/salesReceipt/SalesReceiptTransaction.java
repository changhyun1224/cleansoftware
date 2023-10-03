package payroll.salesReceipt;

import payroll.Transaction;
import payroll.classification.CommissionedClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.SalesReceipt;

public class SalesReceiptTransaction implements Transaction {

    private int itsEmpId;
    private long itsDate;
    private int amount;

    public SalesReceiptTransaction(int itsEmpId, long itsDate, int amount) {
        this.itsEmpId = itsEmpId;
        this.itsDate = itsDate;
        this.amount = amount;
    }

    @Override
    public void execute() throws Exception {
        Employee e = PayrollDatabase.getEmployee(itsEmpId);
        if(e != null) {
            PaymentClassification pc = e.getClassification();
            try {
                CommissionedClassification cc = (CommissionedClassification) pc;
                cc.addSalesReceipt(new SalesReceipt(itsEmpId, itsDate, amount));
            } catch (Exception exception) {
                throw new Exception("Tried to add Sales-Receipt to non-commissioned employee");
            }
        } else {
            throw new Exception("No such employee");
        }
    }

}
