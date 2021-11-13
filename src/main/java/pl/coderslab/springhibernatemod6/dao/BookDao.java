package pl.coderslab.springhibernatemod6.dao;


import org.springframework.stereotype.Repository;
import pl.coderslab.springhibernatemod6.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/*
    Na podstawie przykładu z prezentacji utwórz klasę BookDao.
            Klasa ma realizować podstawowe operacje na encji:

            zapis encji
            edycja encji
            pobieranie po id
            usuwanie po id
*/
@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;
    public void saveBook(Book book) {
        entityManager.persist(book);
    }
    public Book findByID(long id) {
        return entityManager.find(Book.class, id);
    }
    public void update(Book book) {
        entityManager.merge(book);
    }
    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
        }

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :givenRating");
                query.setParameter("givenRating", rating);
                return query.getResultList();
    }
}
