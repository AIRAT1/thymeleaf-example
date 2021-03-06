package de.demo.controller;

import de.demo.domain.Person;
import de.demo.exception.NotFoundException;
import de.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Transactional
public class PersonController {
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    @Transactional(readOnly = true)
    public ModelAndView listPage() {
        ModelAndView mav = new ModelAndView("list");
        List<Person> persons = personRepository.findAll();
        mav.addObject("persons", persons);
        return mav;
    }

    @GetMapping("/edit")
    @Transactional(readOnly = true)
    public ModelAndView editPage(@RequestParam(value = "id", required = false, defaultValue = "1") int id) {
        ModelAndView mav = new ModelAndView("edit");
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        mav.addObject("person", person);
        return mav;
    }

//    @PostMapping("/edit")
//    public String update(WebRequest webRequest, Person person) {
//        String name = webRequest.getParameter("name");
//        person.setName(name);
//        personRepository.save(person);
//        return "edit";
//    }

    @PostMapping("/edit")
    @Transactional(readOnly = true)
    public String update(@RequestParam("name") String name, @Valid Person person) {
        person.setName(name);
        personRepository.save(person);
        return "edit";
    }
}
