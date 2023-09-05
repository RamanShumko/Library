package com.raman.spring.dao;

import com.raman.spring.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDAO {

    List<Book> getAllBook();

    void saveBook(Book book);

    Book getBook(int id);

    void addPersonInBook(int book_id, int person_id);

    void removePersonFromBook(int id);

    void updateBook(Book book);

    void deleteBook(int id);

}
