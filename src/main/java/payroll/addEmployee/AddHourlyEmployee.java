package payroll.addEmployee;

import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.schedule.HourlySchedule;
import payroll.schedule.PaymentSchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction {

    private double rate;

    public AddHourlyEmployee(int empId, String name, String address, double rate) {
        super(empId, name, address);
        this.rate = rate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(rate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new HourlySchedule();
    }

}
