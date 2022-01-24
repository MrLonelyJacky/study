package thinkingJava.Io.xlh;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/5/13.
 */
public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public Student(Element element) {
        this.id = element.getFirstChildElement("id").getValue();
        this.name = element.getFirstChildElement("name").getValue();
    }

    public Element getXml() {
        Element student = new Element("person");
        Element name = new Element("name");
        name.appendChild(this.name);
        Element id = new Element("id");
        id.appendChild(this.id);
        student.appendChild(name);
        student.appendChild(id);
        return student;
    }

    public static void format(OutputStream os, Document doc) throws IOException {
        Serializer serializer = new Serializer(os);
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException {
        /*List<Student> list = new ArrayList<>();
        list.add(new Student("1", "cjq"));
        list.add(new Student("2", "cwh"));
        list.add(new Student("3", "cn"));
        System.out.println(list);
        Element root = new Element("student");
        for (Student student : list) {
            root.appendChild(student.getXml());
        }
        Document document = new Document(root);
        format(System.out, document);
        format(new FileOutputStream("student.xml"), document);*/
    }
}
