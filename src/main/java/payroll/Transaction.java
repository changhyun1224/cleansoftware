package payroll;

/**
 * 목록 19-1 Transaction.h
 *
 * 추상 기반 클래스, execute()라는 이름의 인스턴스 메소드를 갖는다.
 * 커맨드 패턴이다.
 */
public interface Transaction {

    void execute();
}