package com.raman.spring.repositories;

import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepositories extends JpaRepository<Person, Integer> {
    Person findPersonByFullName(String fullName);
}
