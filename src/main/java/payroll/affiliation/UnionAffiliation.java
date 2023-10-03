package payroll.affiliation;

import payroll.entity.ServiceCharge;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {

    private long date;
    private double charge;
    private Map<Long, ServiceCharge> serviceCharges = new HashMap<>();
    private int memberId;

    public UnionAffiliation(long date, double charge) {
        this.date = date;
        this.charge = charge;
    }

    public void addServiceCharge(long date, double charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge));
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public Map<Long, ServiceCharge> getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Map<Long, ServiceCharge> serviceCharges) {
        this.serviceCharges = serviceCharges;
    }
}
