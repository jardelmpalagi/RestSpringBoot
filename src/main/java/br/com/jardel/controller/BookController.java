package br.com.jardel.controller;

import br.com.jardel.data.vo.BookVO;
import br.com.jardel.service.BookService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.jardel.config.WebConfig.APPLICATION_YAML_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public BookVO getPerson(@PathVariable("id") Long id) {

        BookVO bookVO = bookService.findById(id);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).getPerson(id));
        bookVO.add(webMvcLinkBuilder.withRel("self"));

        return bookVO;
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public List<BookVO> getAll() {

        List<BookVO> books = bookService.findAll();
        books.forEach(p -> p.add(linkTo(methodOn(this.getClass()).getAll()).withRel("all")));

        return books;
    }

    @PostMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE},
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public BookVO create(@RequestBody BookVO book) {
        return bookService.create(book);
    }

    @PutMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE},
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public BookVO update(@RequestBody BookVO bookVO) {
        return bookService.update(bookVO);
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }
}
