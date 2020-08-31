package br.com.jardel.controller;

import br.com.jardel.data.vo.PersonVO;
import br.com.jardel.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.jardel.config.WebConfig.APPLICATION_YAML_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Api(value = "Api Person", description = "Api utilizada para gerenciar Person", tags = "Api Person")
@RestController
@RequestMapping("v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation(value = "Retorna um person pelo id")
    @GetMapping(value = "/{id}",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public PersonVO getPerson(@PathVariable("id") Long id) {

        PersonVO personVO = personService.findById(id);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).getPerson(id));
        personVO.add(webMvcLinkBuilder.withRel("self"));

        return personVO;
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public List<PersonVO> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                                       @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sort = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort, "firstName"));

        List<PersonVO> persons = personService.findAll(pageable);
        persons.forEach(p -> p.add(linkTo(methodOn(this.getClass()).getPerson(p.getId())).withSelfRel()));

        return persons;
    }

    @PostMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE},
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PutMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE},
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }

    @ApiOperation(value = "Destiva uma person")
    @PatchMapping(value = "/{id}",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public PersonVO disablePerson(@PathVariable("id") Long id) {

        PersonVO personVO = personService.disblePerson(id);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).getPerson(id));
        personVO.add(webMvcLinkBuilder.withRel("self"));

        return personVO;
    }
}
