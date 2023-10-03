package payroll.changeEmployee;

import payroll.affiliation.Affiliation;
import payroll.affiliation.NoAffiliation;
import payroll.affiliation.UnionAffiliation;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

    public ChangeUnaffiliatedTransaction(int empId) {
        super(empId);
    }

    @Override
    void recordMembership(Employee e) throws Exception {
        Affiliation af = e.getAffiliation();
        try {
            UnionAffiliation uf = (UnionAffiliation) af;
            int memberId = uf.getMemberId();
            PayrollDatabase.removeUnionMember(memberId);
        } catch (Exception exception) {
            throw new Exception("Fail...");
        }
    }

    @Override
    Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}
