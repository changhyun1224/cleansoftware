import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import payroll.addEmployee.AddHourlyEmployee;
import payroll.addEmployee.AddSalariedEmployee;
import payroll.changeEmployee.ChangeMemberTransaction;
import payroll.entity.PayCheck;
import payroll.payday.PaydayTransaction;
import payroll.serviceCharge.ServiceChargeTransaction;
import payroll.timcard.TimeCardTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PayEmployeeTest {

    private int empId = 1;
    private int memberId = 7734;

    // 19-36
    @Test
    public void testPaySingleSalariedEmployee() throws Exception {
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 31);
        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertEquals(PayCheck.class, pc.getClass());
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(1000.00, pc.getGrossPay(), 0.001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions(), 0.001);
        assertEquals(1000.00, pc.getNetPay(), 0.001);
    }

    // 19-40
    @Test
    public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 31);
        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    // 19-41
    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        empId = 2;

        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 31);
        TimeCardTransaction tc = TimeCardTransaction.builder()
                .itsDate(payDate)
                .itsHours(2.0)
                .itsEmpId(empId)
                .build();
        tc.execute();

        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);

        validateHourlyPaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
    }

    // 19-42
    @Test
    public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
        empId = 2;

        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 31);
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .itsDate(payDate)
                .itsHours(9.0)
                .itsEmpId(empId)
                .build();
        timeCardTransaction.execute();

        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck payCheck = pt.getPayCheck(empId);
        assertNull(payCheck);
    }

    // 19-43
    @Test
    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 9);
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .itsDate(payDate)
                .itsHours(2.0)
                .itsEmpId(empId)
                .build();
        timeCardTransaction.execute();

        payDate = new GregorianCalendar(2001, 11, 8);


        TimeCardTransaction timeCardTransaction2 = TimeCardTransaction.builder()
                .itsDate(payDate)
                .itsHours(5.0)
                .itsEmpId(empId)
                .build();
        timeCardTransaction2.execute();

        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        validateHourlyPaycheck(pt, empId, payDate, 7 * 15.25);
    }

    // 19-44
    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        empId = 2;

        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 9);
        TimeCardTransaction tc = TimeCardTransaction.builder()
                .itsDate(payDate)
                .itsHours(2.0)
                .itsEmpId(empId)
                .build();
        tc.execute();

        GregorianCalendar dateInPreviousPayPeriod = new GregorianCalendar(2001, 11, 2);
        TimeCardTransaction tc2 = TimeCardTransaction.builder()
                .itsDate(dateInPreviousPayPeriod)
                .itsHours(5.0)
                .itsEmpId(empId)
                .build();
        tc2.execute();

        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        validateHourlyPaycheck(pt, empId, payDate, 2 * 15.25);
    }

    // 19-47
    @Test
    public void testSalariedUnionMemberDues() throws Exception {
        empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        ase.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 30);
        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        double consumerValue = 2.0;
        validatePayCheck(pt, empId, payDate, 1000.0 - consumerValue);

    }

    // 19-51
    @Test
    public void testHourlyUnionMemberServiceCharge() throws Exception {
        empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 9);
        ServiceChargeTransaction sct = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(payDate)
                .charge(19.42)
                .build();
        sct.execute();

        TimeCardTransaction tct = TimeCardTransaction.builder()
                .itsEmpId(empId)
                .itsDate(payDate)
                .itsHours(8.0)
                .build();
        tct.execute();

        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(8 * 15.24, pc.getGrossPay(), .001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(9.42 + 19.42, pc.getDeductions(), .001);
        assertEquals((8 * 15.24) - (9.42 + 19.42), pc.getNetPay(), .001);
    }

    // 19-52
    @Test
    public void testServiceChargesSpanningMultiplePayPeriods() throws Exception {
        empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        ase.execute();

        memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        GregorianCalendar earlyDate = new GregorianCalendar(2001, 11, 2);
        GregorianCalendar payDate = new GregorianCalendar(2001, 11, 9);
        GregorianCalendar lateDate = new GregorianCalendar(2001, 11, 16);

        ServiceChargeTransaction sct = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(payDate)
                .charge(19.42)
                .build();
        sct.execute();

        ServiceChargeTransaction sctEarly = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(earlyDate)
                .charge(100.00)
                .build();
        sctEarly.execute();

        ServiceChargeTransaction sctLate = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(lateDate)
                .charge(200.00)
                .build();
        sctLate.execute();

        TimeCardTransaction tct = TimeCardTransaction.builder()
                .itsDate(payDate)
                .itsHours(8.0)
                .itsEmpId(empId)
                .build();
        tct.execute();

        PaydayTransaction pt = PaydayTransaction.builder()
                .itsPayDate(payDate)
                .build();
        pt.execute();

        PayCheck pc  = pt.getPayCheck(empId);
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(8 * 15.24, pc.getGrossPay(), .001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(9.42 + 19.42, pc.getDeductions(), .001);
        assertEquals((8 * 15.24) - (9.42 + 19.42), pc.getNetPay(), .001);
    }




















    private void validateHourlyPaycheck(PaydayTransaction paydayTransaction, int empId, Calendar payDate, double pay) {
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertEquals(payDate, payCheck.getPayPeriodEndDate());
        assertEquals(pay, payCheck.getGrossPay(), .001);
        assertEquals("Hold", payCheck.getField("Disposition"));
        assertEquals(0.0, payCheck.getDeductions(), .001);
        assertEquals(1000.00, payCheck.getNetPay(), .001);
    }

    private void validatePayCheck(PaydayTransaction paydayTransaction, int empId, Calendar payDate, double pay) {
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertEquals(payDate, payCheck.getPayPeriodEndDate());
        assertEquals(pay, payCheck.getGrossPay(), .001);
        assertEquals("Hold", payCheck.getField("Disposition"));
        assertEquals(0.0, payCheck.getDeductions(), .001);
        assertEquals(1000.00, payCheck.getNetPay(), .001);
    }

}
