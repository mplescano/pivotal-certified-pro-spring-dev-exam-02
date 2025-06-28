package com.apress.cems.emf;

import com.apress.cems.dao.Person;
import com.apress.cems.emf.component.EmfPersistenceUnitService;
import com.apress.cems.emf.component.TxPersistenceContextPersonService;
import com.apress.cems.emf.config.AppConfig;
import com.apress.cems.emf.config.JpaDbConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaDbConfig.class, AppConfig.class})
public class EmfPersistenceUnitServiceTest {

    @Autowired
    EmfPersistenceUnitService emfPersistenceUnitService;

    @Autowired
    private TxPersistenceContextPersonService txPcPersonService;

    @Test
    void testPerson() {
        Person person = new Person();
        person.setUsername("well.don");
        person.setFirstName("Well");
        person.setLastName("Don");
        person.setPassword("mema");
        person.setHiringDate(LocalDateTime.now());
        emfPersistenceUnitService.insert(person);

        Person personFromTransctionPersistenceContext = txPcPersonService
                .find(person.getId());
        assertNotNull(personFromTransctionPersistenceContext);
    }
}
