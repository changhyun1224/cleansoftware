package payroll.schedule;

import java.util.Calendar;

public class WeeklySchedule implements PaymentSchedule {


    @Override
    public boolean isPayDate(Calendar payDate) {
        return payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        Calendar startDate = (Calendar) payDate.clone();
        startDate.add(Calendar.DAY_OF_MONTH, -5);
        return startDate;
    }
}
