package info.ewai.sbmt.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import info.ewai.sbmt.web.form.BookForm;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Book(BookForm book) {
        this.setBookId(book.getBookId());
        this.setBookName(book.getBookName());
        this.setDescription(book.getDescription());
        this.setLink(book.getLink());
        this.setImg(book.getImg());
        this.setTag(book.getTag());
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long bookId;
    private String bookName;
    private String link;
    private String img;
    private String description;
    private String tag;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
