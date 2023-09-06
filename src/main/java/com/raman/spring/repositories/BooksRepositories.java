package com.raman.spring.repositories;

import com.raman.spring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
}
