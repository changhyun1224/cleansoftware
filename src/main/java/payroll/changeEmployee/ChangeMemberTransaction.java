package payroll.changeEmployee;

import payroll.affiliation.Affiliation;
import payroll.affiliation.UnionAffiliation;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

    private Integer memberId;
    private double dues;

    public ChangeMemberTransaction(int empId, Integer memberId, double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    void recordMembership(Employee e) {
        PayrollDatabase.addUnionMember(memberId, e);
    }

    @Override
    Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }
}
