package br.com.jardel.controller;

import br.com.jardel.data.Person;
import br.com.jardel.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        return personService.findViewById(id);
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }
}
