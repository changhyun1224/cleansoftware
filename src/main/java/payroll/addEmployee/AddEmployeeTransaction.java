package payroll.addEmployee;

import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import payroll.classification.PaymentClassification;
import payroll.method.HoldMethod;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;

import javax.transaction.Transactional;


public abstract class AddEmployeeTransaction implements Transaction {
    private final Integer employeeId;
    private final String name;
    private final String address;

    @Autowired
    private PayrollDatabase employeeRepository;

    protected AddEmployeeTransaction(Integer employeeId, String name, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
    }

    @Transactional
    @Override
    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(employeeId, name, address);
        e.setPaymentClassification(pc);
        e.setPaymentSchedule(ps);
        e.setPaymentMethod(pm);
        employeeRepository.globalPayrollDatabase.addEmployee(employeeId, e);
    }

    abstract PaymentSchedule getSchedule();

    abstract PaymentClassification getClassification();
}
