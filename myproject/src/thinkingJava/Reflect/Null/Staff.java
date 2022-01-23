package thinkingJava.Reflect.Null;

import java.util.ArrayList;

/**
 * Created by 15151 on 2019/4/27.
 */
public class Staff extends ArrayList<Position> {
    public void add(String title, People people) {
        add(new Position(title, people));
    }

    public void add(String... titles) {
        for (String title : titles) {
            add(new Position(title));
        }
    }

    public Staff(String... titles) {
        add(titles);
    }

    public boolean positionAvailable(String title) {
        for (Position position : this) {
            if (position.getTitle().equals(title) && position.getPeople() == People.NULL)
                return true;
        }
        return false;
    }

    public void fillPosition(String title, People hire)  {
        for (Position position : this) {
            if (position.getTitle().equals(title) && position.getPeople() == People.NULL) {
                position.setPeople(hire);
                return;
            }
        }
        throw new RuntimeException("not avaliable");
    }

    public static void main(String[] args) {
        Staff staff = new Staff("cto", "ceo", "manager", "technical manager",
                "Chief inspector", "technical Chief inspector");
        staff.fillPosition("ceo",new People("1","9","bj"));
        staff.fillPosition("cto",new People("3","9","bj"));
        staff.fillPosition("manager",new People("6","9","bj"));
        System.out.println(staff);
    }

}
