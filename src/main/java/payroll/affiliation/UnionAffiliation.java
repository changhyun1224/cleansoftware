package payroll.affiliation;

import lombok.Getter;
import lombok.Setter;
import payroll.classification.PaymentClassification;
import payroll.entity.PayCheck;
import payroll.entity.ServiceCharge;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UnionAffiliation implements Affiliation {

    private long date;
    private double charge;
    private double dues;
    private Map<Long, ServiceCharge> serviceCharges = new HashMap<>();
    private int memberId;
    private PaymentClassification classification;

    public UnionAffiliation(long date, double charge) {
        this.date = date;
        this.charge = charge;
    }

    public void addServiceCharge(long date, double charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge));
    }

    public double calculateDeductions(PayCheck pc) {
        int fridays = numberOfFriddaysInPayPeriod(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());

        return dues * fridays;
    }

    public int numberOfFriddaysInPayPeriod(Calendar payPeriodStartDate, Calendar payPeriodEndDate) {
        int fridays = 0;

        while (payPeriodStartDate.getTimeInMillis() <= payPeriodEndDate.getTimeInMillis()) {
            if (payPeriodStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                fridays++;
        }

        return fridays;
    }

}
