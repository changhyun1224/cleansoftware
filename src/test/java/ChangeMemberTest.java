import org.junit.jupiter.api.*;
import payroll.addEmployee.AddHourlyEmployee;
import payroll.affiliation.Affiliation;
import payroll.affiliation.UnionAffiliation;
import payroll.changeEmployee.ChangeMemberTransaction;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;

public class ChangeMemberTest {

    @Test
    public void changeMemberTest() throws Exception {
        int empId = 2;
        int memberId = 7734;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        Affiliation af = e.getAffiliation();
        Assertions.assertNotNull(af);

        UnionAffiliation uf = (UnionAffiliation) af;
        Assertions.assertEquals(af, uf);
        Assertions.assertEquals(99.42, uf.getCharge());

        Employee member = PayrollDatabase.getUnionMember(memberId);
        Assertions.assertNotNull(member);
        Assertions.assertEquals(e, member);
    }

}
