package pl.coderslab.springhibernatemod6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springhibernatemod6.dao.AuthorDao;
import pl.coderslab.springhibernatemod6.dao.PublisherDao;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Publisher;

@Controller
public class PublisherController {
    private final PublisherDao publisherDao;
    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }
    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("Wydawca 1");
        publisherDao.savePublisher(publisher);
        return "Id dodanego wydawcy to:"
                + publisher.getId();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findPublisherID(id);
        return publisher.toString();
    }
    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updatePublisher (@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findPublisherID(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }
    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findPublisherID(id);
        publisherDao.delete(publisher);
        return "deleted";
    }

}
