package com.raman.spring.dao;

import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class PersonDAOImplementation implements PersonDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> getAllPersons() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> allPersons = session.createQuery("from Person order by fullName", Person.class)
                .getResultList();
        return allPersons;
    }

    @Override
    public void savePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Override
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Override
    public Person getPerson(String fullName) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.createQuery("from Person where fullName =: fullName")
                .setParameter("fullName", fullName);
        return person;
    }

    @Override
    public void updatePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    @Override
    public void deletePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Person.class, id));
    }

    @Override
    public List<Book> busyBooks(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> busyBooks = session.createQuery("from Book where person.id =:id")
                .setParameter("id", id)
                .getResultList();
        return busyBooks;
    }
}
