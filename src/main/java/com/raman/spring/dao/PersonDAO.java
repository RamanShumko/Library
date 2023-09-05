package com.raman.spring.dao;


import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonDAO {

    List<Person> getAllPersons();

    void savePerson(Person person);

    Person getPerson(int id);

    Person getPerson(String fullName);

    void updatePerson(Person person);

    void deletePerson(int id);

    List<Book> busyBooks(int id);
}
