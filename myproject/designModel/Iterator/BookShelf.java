package designModel.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 书架类书籍集合
 */
public class BookShelf implements Aggregate{
    private List<Book> books;


    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public Book getBookAt(int index) {
        return books.get(index);
    }

    public void appendBook(Book book) {
        books.add(book);
    }

    public int getLength() {
        return books.size();
    }

    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
