package com.raman.spring.services;

import com.raman.spring.dao.PersonDAO;
import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImplementation implements PersonService{
    @Autowired
    private PersonDAO personDAO;

    @Override
    @Transactional
    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        personDAO.savePerson(person);
    }

    @Override
    @Transactional
    public Person getPerson(int id) {
        return personDAO.getPerson(id);
    }

    @Override
    @Transactional
    public Person getPerson(String fullName) {
        return personDAO.getPerson(fullName);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    @Override
    @Transactional
    public void deletePerson(int id) {
        personDAO.deletePerson(id);
    }

    @Override
    @Transactional
    public List<Book> busyBooks(int id) {
        return personDAO.busyBooks(id);
    }
}
