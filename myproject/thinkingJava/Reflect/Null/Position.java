package thinkingJava.Reflect.Null;

/**
 * Created by 15151 on 2019/4/27.
 */
public class Position {
    private String title;
    private People people;

    public Position(String title, People people) {
        this.title = title;
        if (people ==null)
            this.people = People.NULL;
        else
            this.people = people;
    }

    public Position(String jobTitle) {
        title = jobTitle;
        people = People.NULL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        if (people == null) {
            this.people = People.NULL;
        }
        this.people = people;
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", people=" + people +
                '}';
    }
}
