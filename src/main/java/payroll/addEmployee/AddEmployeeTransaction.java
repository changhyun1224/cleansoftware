package payroll.addEmployee;

import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.Transaction;

import payroll.classification.PaymentClassification;
import payroll.method.HoldMethod;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;


public abstract class AddEmployeeTransaction implements Transaction {

    private Integer empId;
    private String name;
    private String address;

    public AddEmployeeTransaction(Integer empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public AddEmployeeTransaction() {

    }

    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(empId, name, address);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);
        PayrollDatabase.addEmployee(empId, e);
    }

    abstract PaymentSchedule getSchedule();

    abstract PaymentClassification getClassification();
}
