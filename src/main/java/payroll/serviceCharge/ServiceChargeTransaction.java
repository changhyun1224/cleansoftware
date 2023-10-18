package payroll.serviceCharge;

import lombok.Builder;
import payroll.affiliation.Affiliation;
import payroll.Transaction;
import payroll.affiliation.UnionAffiliation;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

import java.util.Calendar;

@Builder
public class ServiceChargeTransaction implements Transaction {

    private int memberId;
    private Calendar date;
    private double charge;

    public ServiceChargeTransaction(int memberId, Calendar date, double charge) {
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
