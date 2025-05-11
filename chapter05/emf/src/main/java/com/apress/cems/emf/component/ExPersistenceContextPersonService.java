package com.apress.cems.emf.component;

import com.apress.cems.dao.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Extended EntityManagers are <i>not</i> thread-safe, hence they must not be used
 * in concurrently accessed beans (which Spring-managed singletons usually are).
 */
@Component
public class ExPersistenceContextPersonService {

    private EntityManager entityManager;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Person insertWithTransaction(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Person insertWithoutTransaction(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Person find(long id) {
        return entityManager.find(Person.class, id);
    }
}
