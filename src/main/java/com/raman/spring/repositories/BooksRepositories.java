package com.raman.spring.repositories;

import com.raman.spring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
    List<Book> findBookByBookNameStartingWith(String bookName);

    List<Book> findBooksByPerson_Id(int id);

}
