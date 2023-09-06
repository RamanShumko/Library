package com.raman.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;
    @Column(name = "book_name")
    @NotBlank(message = "Book name should not be empty")
    private String bookName;
    @Column(name = "author_name")
    @NotBlank(message = "Author name should not be empty")
    private String authorName;
    @Column(name = "year_of_production")
    @Min(value = 1, message = "Year of production should be greater than 0")
    @Digits(integer = 4, fraction = 0, message = "Number of invalid connection (expected <4 bits>,<0 bits>)")
    private int yearOfProduction;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    public Book() {
    }

    public Book(String bookName, String authorName, int yearOfProduction, Person person) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearOfProduction = yearOfProduction;
        this.person = person;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}
