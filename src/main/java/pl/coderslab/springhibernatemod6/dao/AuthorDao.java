package pl.coderslab.springhibernatemod6.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    EntityManager entityManager;
    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }
    public Author findAuthorID(long id) {
        return entityManager.find(Author.class, id);
    }
    public void update(Author author) {
        entityManager.merge(author);
    }
    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author));
    }
    public List<Author> findAll() {
        Query query = entityManager.createQuery("SELECT a from Author a");
        return query.getResultList();
    }

}
