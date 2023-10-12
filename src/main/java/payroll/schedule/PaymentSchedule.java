package payroll.schedule;


import java.util.Calendar;

public interface PaymentSchedule {

    boolean isPayDate(Calendar payDate);

    Calendar getPayPeriodStartDate(Calendar payDate);
}
