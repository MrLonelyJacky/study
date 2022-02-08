package designModel.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器构建 学院
 */
public class College extends OrganizationComponent {
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String des) {
        super(name, des);
    }

    public void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    public void remove (OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("college------" + getDes() + "-" + getName() + "-------");
        //遍历 organizationComponents
        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }

    }
}
