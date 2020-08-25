package br.com.jardel.service;

import br.com.jardel.PersonRepository;
import static br.com.jardel.converter.MapperConverter.convert;
import br.com.jardel.data.entity.Person;
import br.com.jardel.data.vo.PersonVO;
import br.com.jardel.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonVO create(PersonVO person) {
        var entity = personRepository.save(convert(person, Person.class));

        return convert(entity, PersonVO.class);
    }

    public void delete(Long id) {
        var entity = findEntityById(id);

        personRepository.delete(entity);
    }

    public List<PersonVO> findAll() {
        return convert(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        var entity = findEntityById(id);

        return convert(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO vo) {
        var entity = findEntityById(vo.getId());

        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());

        return convert(personRepository.save(entity), PersonVO.class);
    }

    private Person findEntityById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado a person"));
    }
}
