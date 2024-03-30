package com.example.Namesgraphql.resolver;

import com.example.Namesgraphql.model.Book;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository {
    private static List<Book> books;
    static {
        books = new ArrayList<>();
        books.add(new Book(1, "GraphQL Overview", 100));
        books.add(new Book(2, "Spring Overview", 100));
    }
    public List<Book> allBooks() {
        return books;
    }
    public Book bookById(Integer id) {
        for (Book book : books) {
            if (book.getId() == id)
                return book;
        }
        return null;
    }
    public Book updateBook(Integer id, String title) {
        Book book = bookById(id);
        if (book != null) {
            book.setTitle(title);
        }
        return book;
    }
}
