package mybatis.ognl;

import mybatis.entity.Author;
import mybatis.entity.Blog;
import mybatis.entity.Post;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.apache.ibatis.ognl.OgnlException;
import org.junit.Before;
import org.junit.Test;

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

        context = new OgnlContext();//设置上下文
        context.put("blog",blog);
        context.setRoot(blog);

    }

    @Test
    public void test1() throws OgnlException {
        Author author2 =new Author (2,"username2","password2","email2");
        context.put ("author", author2);
        Object author = Ognl.getValue(Ognl.parseExpression("author"), context, context.getRoot());
        System.out.println(author);
    }
}
