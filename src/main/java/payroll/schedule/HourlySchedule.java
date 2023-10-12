package payroll.schedule;

import java.util.Calendar;

public class HourlySchedule implements PaymentSchedule{
    @Override
    public boolean isPayDate(Calendar payDate) {
        return false;
    }

    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return null;
    }
}
