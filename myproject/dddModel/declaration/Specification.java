package dddModel.declaration;

public interface Specification {
    boolean isSatisfiedBy(Object o);

    Specification and(Specification other);
    Specification or(Specification other);
    Specification not();
}
