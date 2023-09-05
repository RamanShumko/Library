package com.raman.spring.services;

import com.raman.spring.dao.BookDAO;
import com.raman.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImplementation implements BookService {
    @Autowired
    private BookDAO bookDAO;
    @Override
    @Transactional
    public List<Book> getAllBook() {
        return bookDAO.getAllBook();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Override
    @Transactional
    public void updateBook(Book book, int id) {
        book.setId(id);
        bookDAO.updateBook(book);
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    @Override
    @Transactional
    public void addPersonInBook(int book_id, int person_id) {
        bookDAO.addPersonInBook(book_id, person_id);
    }

    @Override
    @Transactional
    public void removePersonFromBook(int id) {
        bookDAO.removePersonFromBook(id);
    }
}
