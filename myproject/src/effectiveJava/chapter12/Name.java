package effectiveJava.chapter12;

import java.io.Serializable;

/**
 * Created by 15151 on 2020/3/24.
 */
public class Name implements Serializable {
    /**
     * last name must be non-null
     * @serial
     */
    private final String lastName;
    /**
     * first name  must be non-null
     * @serial
     */
    private final String firstName;
    /**
     * may be null
     * @serial
     */
    private final String middleName;

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
}
