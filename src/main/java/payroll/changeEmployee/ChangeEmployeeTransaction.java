package payroll.changeEmployee;

import payroll.Transaction;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public abstract class ChangeEmployeeTransaction implements Transaction {

    private int empId;

    public ChangeEmployeeTransaction(int empId) {
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee e = PayrollDatabase.getEmployee(empId);
        if (e != null) {
            change(e);
        }
    }

    abstract void change(Employee e) throws Exception;

}
