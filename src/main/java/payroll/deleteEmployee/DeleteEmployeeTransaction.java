package payroll.deleteEmployee;

import payroll.Transaction;
import payroll.database.PayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public DeleteEmployeeTransaction(int itsEmpId) {
        this.itsEmpId = itsEmpId;
    }

    public void execute() {
        PayrollDatabase.deleteEmployee(itsEmpId);
    }
}
