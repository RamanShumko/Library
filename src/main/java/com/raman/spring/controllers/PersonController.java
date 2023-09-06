package com.raman.spring.controllers;

import com.raman.spring.entity.Person;
import com.raman.spring.services.PersonService;
import com.raman.spring.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;
//    @Autowired
//    private PersonValidator personValidator;

    @GetMapping()
    public String showAllPersons(Model model){
        model.addAttribute("allPersons", personService.getAllPersons());
        return "person/show_all_persons";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id,
                            Model model){
        model.addAttribute("person", personService.getPerson(id));
        model.addAttribute("busyBooks", personService.busyBooks(id));
        return "person/show_person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "person/new_person";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
//        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/new_person";
        }
        personService.savePerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String updatePerson(@PathVariable("id") int id,
                               Model model){
        model.addAttribute("person", personService.getPerson(id));
        return "person/edit_person";
    }

    @PutMapping("/{id}")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
//        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/edit_person";
        }
        personService.updatePerson(person);
        return "redirect:/persons";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/persons";
    }


}
