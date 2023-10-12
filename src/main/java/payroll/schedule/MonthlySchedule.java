package payroll.schedule;

import java.util.Calendar;

public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Calendar payDate) {
        return isLastDayOfMonth(payDate);
    }

    private boolean isLastDayOfMonth(Calendar payDate) {
        int m1 = payDate.get(Calendar.MONTH);
        payDate.add(Calendar.DAY_OF_MONTH, 1);
        int m2 = payDate.get(Calendar.MONTH);
        return m1 != m2;
    }

    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return null;
    }
}
