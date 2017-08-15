package info.ewai.sbmt.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class BookRepositoryTests {

    @Autowired
    private BookRepository repository;

    @Test
    public void test_findByBookNameLikeAndTagLike_findName() throws Exception {
        List<Book> list = this.repository.findByBookNameLikeAndTagLike("%Spring%", "%%");
        assertThat(3).isEqualTo(list.size());
    }

    @Test
    public void test_findByBookNameLikeAndTagLike_findTag() throws Exception {
        List<Book> list = this.repository.findByBookNameLikeAndTagLike("%%", "%Docker%");
        assertThat(1).isEqualTo(list.size());
    }

    @Test
    public void test_findByBookNameLikeAndTagLike() throws Exception {
        List<Book> list = this.repository.findByBookNameLikeAndTagLike("%はじめて%", "%Spring%");
        assertThat(1).isEqualTo(list.size());
    }
}
