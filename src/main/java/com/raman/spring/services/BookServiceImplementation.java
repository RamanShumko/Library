package com.raman.spring.services;

import com.raman.spring.entity.Book;
import com.raman.spring.repositories.BooksRepositories;
import com.raman.spring.repositories.PersonsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

    @Autowired
    private BooksRepositories booksRepositories;

    @Autowired
    private PersonsRepositories personsRepositories;

    @Override
    public List<Book> getAllBook(String sort) {
        List<Book> getAllBooks = booksRepositories.findAll();
        List<Book> getAllBooksSort = booksRepositories
                .findAll(Sort.by("yearOfProduction"));
        if(sort.equals("true")) {
            return getAllBooksSort;
        }else {
            return getAllBooks;
        }
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

    public Book searchBook(String bookName) {
        List<Book> searchBook = booksRepositories.findBookByBookNameStartingWith(bookName);
        Book book;
        if(searchBook.isEmpty()) {
            book = null;
        }else {
            book = searchBook.get(0);
        }
        return book;
    }

    @Override
    public void addPersonInBook(int book_id, int person_id) {
        booksRepositories.findById(book_id)
                .orElse(null)
                .setPerson(personsRepositories.findById(person_id).orElse(null));
    }

    @Override
    public void removePersonFromBook(int id) {
        booksRepositories.findById(id)
                .orElse(null)
                .setPerson(null);
    }
}
