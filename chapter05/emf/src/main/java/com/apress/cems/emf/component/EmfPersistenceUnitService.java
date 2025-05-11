package com.apress.cems.emf.component;

import com.apress.cems.dao.Person;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class EmfPersistenceUnitService {

    EntityManagerFactory entityManagerFactory;

    /**
     * By default spring context registers a internal bean post processor
     * <p>
     * The bean name of the internally managed JPA annotation processor.
     * public static final String PERSISTENCE_ANNOTATION_PROCESSOR_BEAN_NAME =
     * "org.springframework.context.annotation.internalPersistenceAnnotationProcessor";
     * <p>
     * private static final String PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME =
     * "org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor";
     *
     * @param entityManagerFactory
     */
    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Person insert(Person person) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }

        return person;
    }
}
