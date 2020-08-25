package br.com.jardel.service;

import br.com.jardel.PersonRepository;
import br.com.jardel.data.Person;
import br.com.jardel.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public void delete(Long id) {
        Person entity = findViewById(id);

        personRepository.delete(entity);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findViewById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado a person"));
    }

    public Person update(Person person) {
        Person entity = findViewById(person.getId());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }
}
