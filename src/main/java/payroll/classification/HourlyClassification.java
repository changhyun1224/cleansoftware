package payroll.classification;

import payroll.entity.TimeCard;

import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {

    private double hourlyRate;
    private Map<Long, TimeCard> timecards = new HashMap<>();

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        timecards.put(timeCard.getItsDate(), timeCard);
    }

    public TimeCard getTimeCard(long time) {
        return timecards.get(time);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }


}
