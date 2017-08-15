package info.ewai.sbmt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import info.ewai.sbmt.domain.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {

    @Autowired
    BookService bookService;

    @Test
    public void testFindAll() throws Exception {
        List<Book> resultList = this.bookService.findAll();
        assertEquals(5, resultList.size());
    }

    @Test
    public void testFindByBookNameLikeAndTagLike_findAll() throws Exception {
        List<Book> resultList = this.bookService.findByBookNameLikeAndTagLike("", "");
        assertEquals(5, resultList.size());
    }

    @Test
    public void test_findByBookNameLikeAndTagLike_findName() throws Exception {
        List<Book> list = this.bookService.findByBookNameLikeAndTagLike("Spring", "");
        assertThat(3).isEqualTo(list.size());
    }

    @Test
    public void test_findByBookNameLikeAndTagLike_findTag() throws Exception {
        List<Book> list = this.bookService.findByBookNameLikeAndTagLike("", "%Docker%");
        assertThat(1).isEqualTo(list.size());
    }

    @Test
    public void test_findByBookNameLikeAndTagLike() throws Exception {
        List<Book> list = this.bookService.findByBookNameLikeAndTagLike("はじめて", "");
        assertThat(1).isEqualTo(list.size());
    }

    @Test
    public void testFindOne() throws Exception {
        Book result = this.bookService.findOne(new Long(1));
        assertEquals(new Long(1), result.getBookId());
    }

    @Test
    @Rollback
    public void testSave() throws Exception {
        Book book = new Book();
        book.setBookId(new Long(999));
        book.setBookName("test");

        Book result = this.bookService.save(book);
        assertEquals(book.getBookName(), result.getBookName());
    }

    @Test
    @Rollback
    public void testDelete() throws Exception {
        Book book = new Book();
        book.setBookId(new Long(1));
        book.setBookName("test");

        this.bookService.delete(book);
    }
}
