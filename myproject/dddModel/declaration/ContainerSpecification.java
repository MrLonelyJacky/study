package dddModel.declaration;

import dddModel.specificationModel.Container;
import dddModel.specificationModel.ContanierFeature;

public class ContainerSpecification implements Specification{
    private final ContanierFeature contanierFeature;

    public ContainerSpecification(ContanierFeature contanierFeature) {
        this.contanierFeature = contanierFeature;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        if (!(o instanceof Container)){
            return false;
        }
        return ((Container) o).getContanierFeatures().contains(contanierFeature);
    }

    @Override
    public Specification and(Specification other) {
        return null;
    }

    @Override
    public Specification or(Specification other) {
        return null;
    }

    @Override
    public Specification not() {
        return null;
    }
}
