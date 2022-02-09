package mybatis.ognl;

import mybatis.entity.Author;
import mybatis.entity.Blog;
import mybatis.entity.Post;
import org.apache.ibatis.ognl.OgnlContext;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class OgnlTest {
    private static Blog blog;
    private static Author author;
    private static List<Post> posts;
    private static OgnlContext context;

    @Before
    public void start() {
        Blog.staticField = "static Field";
        author = new Author(1, "username1", "password1", "email1");
        Post post = new Post();
        post.setContent("” PostContent”");
        post.setAuthor(author);
        posts = new ArrayList<>();
        posts.add(post);
        blog = new Blog(posts, "title", author, 1);


    }
}
