package com.raman.spring.services;

import com.raman.spring.entity.Book;
import com.raman.spring.repositories.BooksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

    @Autowired
    private BooksRepositories booksRepositories;

    @Override
    public List<Book> getAllBook() {
        return booksRepositories.findAll();
    }

    @Override
    public void saveBook(Book book) {
        booksRepositories.save(book);
    }

    @Override
    public Book getBook(int id) {
        Optional<Book> book = booksRepositories.findById(id);
        return book.orElse(null);
    }

    @Override
    public void updateBook(Book book, int id) {
        book.setId(id);
        booksRepositories.save(book);
    }

    @Override
    public void deleteBook(int id) {
        booksRepositories.deleteById(id);
    }

    @Override
    public void addPersonInBook(int book_id, int person_id) {
        booksRepositories.savePerson(book_id, person_id);
    }

    @Override
    public void removePersonFromBook(int id) {
        booksRepositories.removePersonById(id);
    }
}
