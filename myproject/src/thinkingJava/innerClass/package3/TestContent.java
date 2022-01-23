package thinkingJava.innerClass.package3;
import thinkingJava.innerClass.package1.Content;
import thinkingJava.innerClass.package2.Book;

/**
 * Created by 15151 on 2019/2/19.
 */
public class TestContent {
    public static void main(String[] args) {
        Book book = new Book();
        //因为BookContent是protected类型的所以在包3中无法获取到
        //Book.BookContent content1 = book.getContent();
        Content content = book.getContent();
        content.say();
    }
}
