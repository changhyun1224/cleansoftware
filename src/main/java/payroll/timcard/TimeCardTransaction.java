package payroll.timcard;

import payroll.Transaction;
import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.TimeCard;

public class TimeCardTransaction implements Transaction {

    private int itsEmpId;
    private long itsDate;
    private double itsHours;

    public TimeCardTransaction(int itsEmpId, long itsDate, double itsHours) {
        this.itsEmpId = itsEmpId;
        this.itsDate = itsDate;
        this.itsHours = itsHours;
    }

    @Override
    public void execute() throws Exception {

        Employee e = PayrollDatabase.getEmployee(itsEmpId);

        if (e != null) {
            PaymentClassification pc = e.getClassification();
            try {
                HourlyClassification hc = (HourlyClassification) pc;
                hc.addTimeCard(new TimeCard(itsDate, itsHours));
            } catch (Exception exception) {
                throw new Exception("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new Exception("No such employee.");
        }

    }
}
