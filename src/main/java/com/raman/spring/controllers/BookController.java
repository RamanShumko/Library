package com.raman.spring.controllers;

import com.raman.spring.entity.Book;
import com.raman.spring.entity.Person;
import com.raman.spring.services.BookService;
import com.raman.spring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PersonService personService;

    @GetMapping()
    public String getAllBooks(Model model){
        model.addAttribute("allBooks", bookService.getAllBook());
        return "book/show_all_books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("allPerson", personService.getAllPersons());
        model.addAttribute("book", bookService.getBook(id));
        model.addAttribute("person", bookService.getBook(id).getPerson());
        model.addAttribute("personId", new Person());
        return "book/show_book";
    }

    @PatchMapping ("/{id}/person")
    public String addPersonInBook(@PathVariable("id") int book_id,
                            @RequestParam("id") int person_id){
        bookService.addPersonInBook(book_id, person_id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/remove/person")
    public String removePersonFromBook(@PathVariable("id") int id){
        bookService.removePersonFromBook(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new_book";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new_book";
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBook(id));
        return "book/edit_book";
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable("id") int id,
                           @ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/edit_book";
        }
        bookService.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
