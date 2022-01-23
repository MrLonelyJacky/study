package refactoring.chapter7;

/**
 * hide delegeate
 */
public class Department {
    private String chargeCode;
    private Person manager;

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }
}
