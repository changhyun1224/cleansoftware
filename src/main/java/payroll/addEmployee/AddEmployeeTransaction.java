package payroll.addEmployee;

import payroll.Employee;
import payroll.EmployeeRepository;
import payroll.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.classification.PaymentClassification;
import payroll.method.HoldMethod;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;

import javax.transaction.Transactional;


@Service
public abstract class AddEmployeeTransaction implements Transaction {
    private final Integer empId;
    private final String name;
    private final String address;

    @Autowired
    private EmployeeRepository employeeRepository;

    protected AddEmployeeTransaction(Integer empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    @Transactional
    @Override
    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(empId, name, address);
        e.setPaymentClassification(pc);
        e.setPaymentSchedule(ps);
        e.setPaymentMethod(pm);
        employeeRepository.save(e);
    }

    abstract PaymentSchedule getSchedule();

    abstract PaymentClassification getClassification();
}
