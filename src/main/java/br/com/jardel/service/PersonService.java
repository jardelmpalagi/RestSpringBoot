package br.com.jardel.service;

import br.com.jardel.data.entity.Person;
import br.com.jardel.data.vo.PersonVO;
import br.com.jardel.exception.ResourceNotFoundException;
import br.com.jardel.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.jardel.converter.MapperConverter.convert;

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

    public Page<PersonVO> findAll(Pageable pageable) {
        var page = personRepository.findAll(pageable);
        return page.map(p -> convert(p, PersonVO.class));
    }

    public Page<PersonVO> findAllByFirstName(String firstName, Pageable pageable) {
        var page = personRepository.findByLikeFirstName(firstName, pageable);
        return page.map(p -> convert(p, PersonVO.class));
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
        entity.setGender(vo.getGender());
        entity.setEnabled(vo.getEnabled());

        return convert(personRepository.save(entity), PersonVO.class);
    }

    @Transactional
    public PersonVO disblePerson(Long id) {
        personRepository.disablePerson(id);

        return convert(findEntityById(id), PersonVO.class);
    }

    private Person findEntityById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado a person"));
    }
}
