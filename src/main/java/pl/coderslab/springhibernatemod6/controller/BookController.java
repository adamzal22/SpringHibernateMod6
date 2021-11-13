package pl.coderslab.springhibernatemod6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springhibernatemod6.dao.AuthorDao;
import pl.coderslab.springhibernatemod6.dao.BookDao;
import pl.coderslab.springhibernatemod6.dao.PublisherDao;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Book;
import pl.coderslab.springhibernatemod6.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;

    }
    @RequestMapping("/saveWithExistingPublisher")
    @ResponseBody
    public String saveWithExistingPublisher() {
        Publisher publisher = publisherDao.findPublisherID(3);
        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setDescription("Ksiązka o pustyni i puszczy");
        book.setPublisher(publisher);
        bookDao.saveBook(book);
        return book.toString();
    }

    @RequestMapping("/book/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        Author firstAuthor = authorDao.findAuthorID(2);
        Author secondAuthor = authorDao.findAuthorID(3);
        publisher.setName("PWN");
        publisherDao.savePublisher(publisher);
        Book book = new Book();
        book.setTitle("Władca Javy");
        book.setDescription("Bruce Eckel w środku konfliktu Hobbitów");
        book.getAuthors().add(firstAuthor);
        book.getAuthors().add(secondAuthor);
        book.setPublisher(publisher);
        bookDao.saveBook(book);
        return "Id dodanej książki to:"
                + book.getId();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findByID(id);
        return book.toString();
    }
    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook (@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findByID(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }
    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findByID(id);
        bookDao.delete(book);
        return "deleted";
    }


    @RequestMapping("/book/all")
    @ResponseBody
    public String findAll() {
        List<Book> allBooks = bookDao.findAll();
        return allBooks.stream()
                .map(book -> book.getId() + " : " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }

    @RequestMapping("/book/rating/{rating}")
    @ResponseBody
    public String findAllByRating(@PathVariable int rating) {
        List<Book> allBooksByRating = bookDao.findAllByRating(rating);
        return allBooksByRating.stream()
                .map(book -> book.getId() + " : " + book.getTitle() + " : " + book.getRating())
                .collect(Collectors.joining("<br />"));
    }
}
