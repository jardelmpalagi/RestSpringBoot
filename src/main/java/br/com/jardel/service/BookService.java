package br.com.jardel.service;

import br.com.jardel.data.entity.Book;
import br.com.jardel.data.vo.BookVO;
import br.com.jardel.exception.ResourceNotFoundException;
import br.com.jardel.repository.BookRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

import static br.com.jardel.converter.MapperConverter.convert;

@Controller
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookVO create(BookVO bookVO) {
        var entity = bookRepository.save(convert(bookVO, Book.class));

        return convert(entity, BookVO.class);
    }

    public void delete(Long id) {
        var entity = findEntityById(id);

        bookRepository.delete(entity);
    }

    public List<BookVO> findAll() {
        return convert(bookRepository.findAll(), BookVO.class);
    }

    public BookVO findById(Long id) {
        var entity = findEntityById(id);

        return convert(entity, BookVO.class);
    }

    public BookVO update(BookVO vo) {
        var entity = findEntityById(vo.getId());

        entity.setAuthor(vo.getAuthor());
        entity.setLaunchDate(vo.getLaunchDate());
        entity.setPrice(vo.getPrice());
        entity.setTitle(vo.getTitle());

        return convert(bookRepository.save(entity), BookVO.class);
    }

    private Book findEntityById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book não encontrado!"));
    }
}
