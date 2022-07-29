package dddModel.specificationModel;

public class ContainerSpecification {
    private ContanierFeature contanierFeature;

    public ContainerSpecification(ContanierFeature contanierFeature) {
        this.contanierFeature = contanierFeature;
    }

    public boolean isSatisfiedBy(Container container){
        return container.getContanierFeatures().contains(contanierFeature);
    }


}
