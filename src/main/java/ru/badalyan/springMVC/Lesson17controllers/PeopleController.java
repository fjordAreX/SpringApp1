package ru.badalyan.springMVC.Lesson17controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.badalyan.springMVC.Lesson17DAO.PersonDAO;
import ru.badalyan.springMVC.Lesson17models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());

    return "people/index";
}

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model ){
        model.addAttribute("person",personDAO.show(id));

    return "people/show";
}


    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("personForExampleManyWords",new Person());
        return "people/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("personForExampleManyWords") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/create";
        }

        personDAO.save(person);
        return "redirect:/people"; // он по url перейдет на localhost:people
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("personAnotherExampleOfManyWords",personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("personAnotherExampleOfManyWords") @Valid Person person,BindingResult bindingResult,@PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        personDAO.update(id,person);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
