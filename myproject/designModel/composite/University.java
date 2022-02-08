package designModel.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器构建 大学
 */
public class University extends OrganizationComponent{
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String des) {
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
        System.out.println("university----"+getDes()+"-"+getName());
        for (OrganizationComponent organizationComponent : organizationComponents){
            organizationComponent.print();
        }
    }
}
