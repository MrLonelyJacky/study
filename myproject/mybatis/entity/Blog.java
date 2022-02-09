package mybatis.entity;

import java.util.List;

public class Blog {
    private List<Post> posts;
    public static String staticField;
    private String title;
    private Author author;
    private int type;

    public Blog(List<Post> posts, String title, Author author, int type) {
        this.posts = posts;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public static String getStaticField() {
        return staticField;
    }

    public static void setStaticField(String staticField) {
        Blog.staticField = staticField;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
