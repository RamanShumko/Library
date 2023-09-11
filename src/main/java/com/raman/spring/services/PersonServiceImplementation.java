package com.raman.spring.services;

import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import com.raman.spring.repositories.BooksRepositories;
import com.raman.spring.repositories.PersonsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImplementation implements PersonService{

    @Autowired
    private PersonsRepositories personsRepositories;

    @Autowired
    private BooksRepositories booksRepositories;

    @Override
    public List<Person> getAllPersons() {
        return personsRepositories.findAll();
    }

    @Override
    public void savePerson(Person person) {
        personsRepositories.save(person);
    }

    @Override
    public Person getPerson(int id) {
        Optional<Person> person = personsRepositories.findById(id);
        return person.orElse(null);
    }

    @Override
    public Person getPerson(String fullName) {
        return personsRepositories.findPersonByFullName(fullName);
    }

    @Override
    public void updatePerson(Person person) {
        personsRepositories.save(person);
    }

    @Override
    public void deletePerson(int id) {
        personsRepositories.deleteById(id);
    }

    @Override
    public List<Book> findBooksByPerson_id(int id) {
        return booksRepositories.findBooksByPerson_Id(id);
    }
}
