package pl.coderslab.springhibernatemod6.dao;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springhibernatemod6.entity.Author;
import pl.coderslab.springhibernatemod6.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {
    @PersistenceContext
    EntityManager entityManager;
    public void addPerson(Person person) {
        entityManager.persist(person);
    }
    public Person findByID(long id) {
        return entityManager.find(Person.class, id);
    }
    public void update(Person person) {
        entityManager.merge(person);
    }
    public void delete(Person person) {
        entityManager.remove(entityManager.contains(person) ?
                person : entityManager.merge(person));
    }

}