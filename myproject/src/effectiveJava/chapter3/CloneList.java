package effectiveJava.chapter3;

import java.util.List;

public class CloneList {
    private String id;
    private List<CloneList> cloneLists;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CloneList> getCloneLists() {
        return cloneLists;
    }

    public void setCloneLists(List<CloneList> cloneLists) {
        this.cloneLists = cloneLists;
    }

    @Override
    public String toString() {
        return "CloneList{" +
                "id='" + id + '\'' +
                ", cloneLists=" + cloneLists +
                '}';
    }
}
