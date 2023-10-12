package payroll.classification;

import payroll.entity.PayCheck;

import java.util.Calendar;

public interface PaymentClassification {

    public double calculatePay(PayCheck pc);

    public static boolean isInPayPeriod(Calendar theDate, PayCheck pc) {
        Calendar payPeriodStartDate = pc.getPayPeriodStartDate();
        Calendar payPeriodEndDate = pc.getPayPeriodEndDate();
        return theDate.after(payPeriodStartDate) && theDate.before(payPeriodEndDate);
    }

}
