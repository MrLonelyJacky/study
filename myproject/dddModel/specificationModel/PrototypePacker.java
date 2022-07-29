package dddModel.specificationModel;

import java.util.Iterator;
import java.util.Set;

public class PrototypePacker implements WarehousePacker{

    @Override
    public void pack(Set<Container> containersToFill, Set<Drum> drumsToPack) {
        for (Drum drum : drumsToPack) {
            Container containerFor = findContainerFor(containersToFill, drum);
            containerFor.getContents().add(drum);
        }
    }

    public Container findContainerFor(Set<Container> containersToFill, Drum drum) {
        for (Container container : containersToFill) {
            if (container.canAccommodate(drum)) {
                return container;
            }
        }
        throw new IllegalStateException("no answers");
    }
}
