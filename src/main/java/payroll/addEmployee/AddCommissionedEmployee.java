package payroll.addEmployee;

import payroll.classification.CommissionedClassification;
import payroll.classification.PaymentClassification;
import payroll.schedule.BiweeklySchedule;
import payroll.schedule.PaymentSchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction  {

    private double salary;
    private double commissionRate;

    public AddCommissionedEmployee() {
    }

    public AddCommissionedEmployee(int itsEmpId, String itsName, String itsAddress, double salary, double commissionRate) {
        super(itsEmpId, itsName, itsAddress);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
