package com.raman.spring.services;

import com.raman.spring.entity.Book;
import com.raman.spring.repositories.BooksRepositories;
import com.raman.spring.repositories.PersonsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public List<Book> getAllBook(String sort, String page, String booksPerPage) {
        int i = 0;
        if (sort == null) {
            i = 0;
        } else if (sort.equals("true")) {
            i = 1;
        }
        if(page != null && booksPerPage != null && i == 1) {
            return booksRepositories
                    .findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(booksPerPage), Sort.by("yearOfProduction")))
                    .getContent();
        } else if((page == null || booksPerPage == null) && i == 0){
            return booksRepositories.findAll();

        } else if (i == 1) {
            return booksRepositories
                    .findAll(Sort.by("yearOfProduction"));
        } else {
            return booksRepositories
                    .findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(booksPerPage)))
                    .getContent();
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

    public List<Book> searchBook(String bookName) {
        List<Book> foundBooks = booksRepositories.findBookByBookNameStartingWith(bookName);
        return foundBooks;
    }

    @Override
    public void addPersonInBook(int book_id, int person_id) {
        Book book = booksRepositories.findById(book_id).orElse(null);
        book.setPerson(personsRepositories.findById(person_id).orElse(null));
        book.setBookAssignmentDate(new Date());
    }

    @Override
    public void removePersonFromBook(int id) {
        Book book = booksRepositories.findById(id).orElse(null);
        book.setPerson(null);
        book.setBookAssignmentDate(null);
    }
}
