package dddModel.specificationModel;

import java.util.Iterator;
import java.util.Set;

public class Container {
    private double capacity;
    private Set<Drum> contents;

    private Set<ContanierFeature> contanierFeatures;

    public boolean hasSpaceFor(Drum drum) {
        return remaninigSpace() >= drum.getSize();
    }

    public double remaninigSpace() {
        double totalContentSize = 0.0;
        Iterator<Drum> iterator = contents.iterator();
        while (iterator.hasNext()) {
            Drum next = iterator.next();
            totalContentSize = totalContentSize + next.getSize();
        }
        return capacity = totalContentSize;
    }

    public boolean isSafelyPacked(){
        Iterator<Drum> iterator = contents.iterator();
        while (iterator.hasNext()){
            Drum drum = iterator.next();
            if (drum.getContainerSpecification().isSatisfiedBy(this))
                return true;
        }
        return false;
    }

    public boolean canAccommodate(Drum drum){
        return hasSpaceFor(drum) && drum.getContainerSpecification().isSatisfiedBy(this);
    }


    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Set<Drum> getContents() {
        return contents;
    }

    public void setContents(Set<Drum> contents) {
        this.contents = contents;
    }

    public Set<ContanierFeature> getContanierFeatures() {
        return contanierFeatures;
    }

    public void setContanierFeatures(Set<ContanierFeature> contanierFeatures) {
        this.contanierFeatures = contanierFeatures;
    }
}
