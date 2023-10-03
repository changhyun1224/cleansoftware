package payroll.changeEmployee;

import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.schedule.PaymentSchedule;
import payroll.schedule.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {

    private double hourlyDate;

    public ChangeHourlyTransaction(int empId, double hourlyDate) {
        super(empId);
        this.hourlyDate = hourlyDate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyDate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

}
