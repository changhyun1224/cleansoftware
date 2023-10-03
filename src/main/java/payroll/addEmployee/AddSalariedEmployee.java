package payroll.addEmployee;

import payroll.classification.PaymentClassification;
import payroll.classification.SalariedClassification;
import payroll.schedule.MonthlySchedule;
import payroll.schedule.PaymentSchedule;

public class AddSalariedEmployee extends AddEmployeeTransaction {

    private double salary;

    public AddSalariedEmployee(int empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = this.salary;
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }
}
