package br.com.jardel.controller;

import br.com.jardel.data.entity.Person;
import br.com.jardel.data.vo.PersonVO;
import br.com.jardel.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}")
    public PersonVO getPerson(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @GetMapping
    public List<PersonVO> getAll() {
        return personService.findAll();
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }
}
