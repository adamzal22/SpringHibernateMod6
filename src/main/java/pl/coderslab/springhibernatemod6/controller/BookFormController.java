package pl.coderslab.springhibernatemod6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.springhibernatemod6.dao.AuthorDao;
import pl.coderslab.springhibernatemod6.dao.BookDao;
import pl.coderslab.springhibernatemod6.dao.PublisherDao;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Book;
import pl.coderslab.springhibernatemod6.entity.Publisher;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookFormController {

    private PublisherDao publisherDao;
    private BookDao bookDao;
    private AuthorDao authorDao;


    public BookFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @ModelAttribute("ratings")
    public List<String> ratings() {
        return Arrays.asList("1", "2", "3", "4", "5", "6");
    }

    @GetMapping("/form")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "/book/bookForm";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookDao.saveBook(book);
        return "redirect:/book/all";

    }

    @GetMapping("/all")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "/book/bookList";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors() {
        return authorDao.findAll();
    }
}


