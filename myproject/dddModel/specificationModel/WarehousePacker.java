package dddModel.specificationModel;

import java.util.Set;

public interface WarehousePacker {
    public void pack(Set<Container> containersToFill,Set<Drum> drumsToPack);
}
