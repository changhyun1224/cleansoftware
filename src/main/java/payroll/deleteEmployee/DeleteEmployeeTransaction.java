package payroll.deleteEmployee;

import payroll.Transaction;
import payroll.database.PayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {
    private int employeeId;

    public DeleteEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }


    public void execute() {
        PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
        database.deleteEmployee(employeeId);
    }
}
