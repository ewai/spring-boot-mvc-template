package info.ewai.sbmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import info.ewai.sbmt.domain.Book;
import info.ewai.sbmt.domain.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public List<Book> findByBookNameLikeAndTagLike(String bookName, String tag) {
        if (StringUtils.isEmpty(bookName) && (StringUtils.isEmpty(tag))) {
            return this.findAll();
        }
        return this.bookRepository.findByBookNameLikeAndTagLike("%" + bookName + "%", "%" + tag + "%");
    }

    public Book findOne(Long bookId) {
        return this.bookRepository.findOne(bookId.longValue());
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

}