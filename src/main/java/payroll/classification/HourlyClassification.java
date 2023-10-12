package payroll.classification;

import payroll.entity.PayCheck;
import payroll.entity.TimeCard;

import java.util.ArrayList;
import java.util.Calendar;

public class HourlyClassification implements PaymentClassification {

    private double hourlyRate;
    public ArrayList<TimeCard> timecards;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        timecards.add(timeCard);
    }

    public TimeCard getTimeCard(Calendar date) {
        for (TimeCard t : timecards) {
            if (t.getItsDate().equals(date)) return t;
        }
        return null;
    }

    @Override
    public double calculatePay(PayCheck pc) {
        double totalPay = 0;

        for (TimeCard tc : timecards) {
            if (PaymentClassification.isInPayPeriod(tc.getItsDate(), pc))
                totalPay += calculatePayForTimeCard(tc);
        }
        return totalPay;
    }

    public double calculatePayForTimeCard(TimeCard tc) {
        double hours = tc.getItsHours();
        double overTime = Math.max(0.0, hours - 8.0);
        double straightTime = hours - overTime;
        return (straightTime * hourlyRate) + ((overTime * hourlyRate) * 1.5);
    }
}
