package com.raman.spring.services;

import com.raman.spring.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BookService {

    List<Book> getAllBook();

    void saveBook(Book book);

    Book getBook(int id);

    void updateBook(Book book, int id);

    void deleteBook(int id);

//    void addPersonInBook(int book_id, int person_id);
//
//    void removePersonFromBook(int id);
}
