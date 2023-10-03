package payroll.serviceCharge;

import payroll.affiliation.Affiliation;
import payroll.Transaction;
import payroll.affiliation.UnionAffiliation;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public class ServiceChargeTransaction implements Transaction {

    private int memberId;
    private long date;
    private double charge;

    public ServiceChargeTransaction(int memberId, long date, double charge) {
        this.memberId = memberId;
        this.date = date;
        this.charge = charge;
    }


    @Override
    public void execute() throws Exception {
        Employee e = PayrollDatabase.getUnionMember(memberId);
        Affiliation af = e.getAffiliation();
        try {
            UnionAffiliation uaf = (UnionAffiliation) af;
            uaf.addServiceCharge(date, charge);
        } catch (Exception exception) {
            throw new Exception("Fail...");
        }

    }
}
