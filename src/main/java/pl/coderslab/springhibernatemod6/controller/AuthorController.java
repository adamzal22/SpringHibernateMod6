package pl.coderslab.springhibernatemod6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springhibernatemod6.dao.AuthorDao;
import pl.coderslab.springhibernatemod6.dao.BookDao;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Book;

@Controller
public class AuthorController {
    private final AuthorDao authorDao;
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    @RequestMapping("/author/add")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("J.R.R.");
        author.setLastName("Tolkien");
        authorDao.saveAuthor(author);
        return "Id dodanego autora to:"
                + author.getId();
    }

    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Author author = authorDao.findAuthorID(id);
        return author.toString();
    }
    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String updateAuthor (@PathVariable long id, @PathVariable String lastName, @PathVariable String firstName) {
        Author author = authorDao.findAuthorID(id);
        author.setLastName(lastName);
        author.setFirstName(firstName);
        authorDao.update(author);
        return author.toString();
    }
    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.findAuthorID(id);
        authorDao.delete(author);
        return "deleted";
    }

}
