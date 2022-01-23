package thinkingJava.innerClass.package2;

import thinkingJava.innerClass.package1.Content;

/**
 * Created by 15151 on 2019/2/19.
 */
public class Book {
    protected class BookContent implements Content{

        @Override
        public void say() {
            System.out.println("i am a book content");
        }
    }

    public BookContent getContent(){
        return new BookContent();
    }
}
