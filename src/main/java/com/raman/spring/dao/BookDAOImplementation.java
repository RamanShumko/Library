package com.raman.spring.dao;

import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAOImplementation implements BookDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBook() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> allBooks = session.createQuery("from Book order by bookName", Book.class)
                .getResultList();
        return allBooks;
    }

    @Override
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Override
    public Book getBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return book;
    }

    @Override
    public void addPersonInBook(int book_id, int person_id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, person_id);
        Book book = session.get(Book.class, book_id);
        book.setPerson(person);
        person.addNewBook(book);
    }

    @Override
    public void removePersonFromBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.getPerson().getListBook().remove(book);
        book.setPerson(null);
    }

    @Override
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        Book book1 = session.get(Book.class, book.getId());
        book1.setBookName(book.getBookName());
        book1.setAuthorName(book.getAuthorName());
        book1.setYearOfProduction(book.getYearOfProduction());
    }

    @Override
    public void deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Book where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
