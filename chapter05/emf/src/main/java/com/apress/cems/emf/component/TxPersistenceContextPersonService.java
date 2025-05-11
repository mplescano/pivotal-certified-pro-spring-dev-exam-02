package com.apress.cems.emf.component;

import com.apress.cems.dao.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TxPersistenceContextPersonService {

    private EntityManager entityManager;

    @PersistenceContext
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
