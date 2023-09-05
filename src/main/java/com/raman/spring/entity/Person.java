package com.raman.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    @NotBlank(message = "Full name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Your full name should be this format: Name Surname")
    @Column(name = "full_name")
    String fullName;
    @Min(value = 1900, message = "Year of birth should be greater than 1899")
    @Max(value = 2023, message = "Year of birth should be less than 2024")
    @Digits(integer = 4, fraction = 0, message = "Number of invalid connection (expected <4 bits>,<0 bits>)")
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @OneToMany(mappedBy = "person")
    private List<Book> listBook;

    public Person() {
    }

    public Person(String fullName, int yearOfBirth, List<Book> listBook) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.listBook = listBook;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    public void addNewBook(Book book) {
        if(listBook == null){
            listBook = new ArrayList<>();
        }
        listBook.add(book);
        book.setPerson(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth;
    }
}
