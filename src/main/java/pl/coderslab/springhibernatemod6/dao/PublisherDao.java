package pl.coderslab.springhibernatemod6.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;
    public void savePublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }
    public Publisher findPublisherID(long id) {
        return entityManager.find(Publisher.class, id);
    }
    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }
    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ?
                publisher : entityManager.merge(publisher));
    }
    public List<Publisher> findAll() {
        Query query = entityManager.createQuery("SELECT p from Publisher p");
        return query.getResultList();
    }
}
