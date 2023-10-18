package payroll.timcard;

import lombok.Builder;
import payroll.Transaction;
import payroll.classification.HourlyClassification;
import payroll.classification.PaymentClassification;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.TimeCard;

import java.util.Calendar;

@Builder
public class TimeCardTransaction implements Transaction {

    private int itsEmpId;
    private Calendar itsDate;
    private double itsHours;

    public TimeCardTransaction(int itsEmpId, Calendar itsDate, double itsHours) {
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
