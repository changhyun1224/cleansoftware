package payroll;

import payroll.serviceCharge.Affiliation;

public class UnionAffiliation implements Affiliation {

    private final long date;
    private final double charge;

    public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(-1,0);

    public UnionAffiliation(long date, double charge) {
        this.date = date;
        this.charge = charge;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {

    }

}
