package com.apress.cems.emf;

import com.apress.cems.dao.Person;
import com.apress.cems.emf.component.ExPersistenceContextPersonService;
import com.apress.cems.emf.component.TxPersistenceContextPersonService;
import com.apress.cems.emf.config.AppConfig;
import com.apress.cems.emf.config.JpaDbConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.TransactionRequiredException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @see //www.baeldung.com/jpa-hibernate-persistence-context
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaDbConfig.class, AppConfig.class})
public class ExTxPersistenceContextTest {

    @Autowired
    private TxPersistenceContextPersonService txPcPersonService;

    @Autowired
    private ExPersistenceContextPersonService exPcPersonService;

    @Test
    void testThatPersonUsingSaveTransaction() {
        Person person = new Person();
        person.setUsername("well.don");
        person.setFirstName("Well");
        person.setLastName("Don");
        person.setPassword("mema");
        person.setHiringDate(LocalDateTime.now());
        txPcPersonService.insertWithTransaction(person);

        Person personFromTransctionPersistenceContext = txPcPersonService
                .find(person.getId());
        assertNotNull(personFromTransctionPersistenceContext);

        Person personFromExtendedPersistenceContext = exPcPersonService
                .find(person.getId());
        assertNotNull(personFromExtendedPersistenceContext);
    }

    @Test
    public void testThatPersonSaveWithoutTransactionThrowException() {
        Person person = new Person();
        person.setUsername("beken.fuht");
        person.setFirstName("Beken");
        person.setLastName("Fuht");
        person.setPassword("chicha");
        person.setHiringDate(LocalDateTime.now());

        assertThrows(TransactionRequiredException.class, () ->
                txPcPersonService.insertWithoutTransaction(person)
        );
    }

    @Test
    void testThatPersonUsingExtendedSaveTransaction() {
        Person person = new Person();
        person.setUsername("foca.torel");
        person.setFirstName("Foca");
        person.setLastName("Torel");
        person.setPassword("ghyhgkj");
        person.setHiringDate(LocalDateTime.now());
        exPcPersonService.insertWithoutTransaction(person);

        Person personFromExtendedPersistenceContext = exPcPersonService
                .find(person.getId());
        assertNotNull(personFromExtendedPersistenceContext);

        Person personFromTransctionPersistenceContext = txPcPersonService
                .find(person.getId());
        assertNull(personFromTransctionPersistenceContext);
    }

    /**
     * TODO, replicate this
     *
     * @Test(expected = EntityExistsException.class)
     * public void testThatPersistUserWithSameIdentifierThrowException() {
     * User user1 = new User(126L, "Devender", "admin");
     * User user2 = new User(126L, "Devender", "admin");
     * extendedPersistenceContext.insertWithoutTransaction(user1);
     * extendedPersistenceContext.insertWithoutTransaction(user2);
     * }
     */
    @Test
    public void testThatPersonExtendedSaveWithoutTransactionThrowException() {
        LocalDateTime hiringDate = LocalDateTime.now();
        Person person1 = new Person();
        person1.setUsername("beken.fuht");
        person1.setFirstName("Beken");
        person1.setLastName("Fuht");
        person1.setPassword("chicha");
        person1.setHiringDate(hiringDate);

        exPcPersonService.insertWithoutTransaction(person1);

        Person person2 = new Person();
        person2.setUsername("beken.fuht");
        person2.setFirstName("Beken");
        person2.setLastName("Fuht");
        person2.setPassword("chicha");
        person2.setHiringDate(hiringDate);
        exPcPersonService.insertWithoutTransaction(person2);

        assertNotNull(person1.getId());
        assertNotNull(person2.getId());

        Person person1FromTxPersistenceContext = txPcPersonService
                .find(person1.getId());
        assertNull(person1FromTxPersistenceContext);
        Person person2FromTxPersistenceContext = txPcPersonService
                .find(person2.getId());
        assertNull(person2FromTxPersistenceContext);

        Person person1FromExPersistenceContext = exPcPersonService
                .find(person1.getId());
        assertNotNull(person1FromExPersistenceContext);
        Person person2FromExPersistenceContext = exPcPersonService
                .find(person2.getId());
        assertNotNull(person2FromExPersistenceContext);

        assertThrows(JpaSystemException.class, () ->
                exPcPersonService.insertWithTransaction(person1)
        );

        person1FromExPersistenceContext = exPcPersonService
                .find(person1.getId());
        assertNull(person1FromExPersistenceContext);
        person2FromExPersistenceContext = exPcPersonService
                .find(person2.getId());
        assertNull(person2FromExPersistenceContext);

        person1FromTxPersistenceContext = txPcPersonService
                .find(person1.getId());
        assertNull(person1FromTxPersistenceContext);
        person2FromTxPersistenceContext = txPcPersonService
                .find(person2.getId());
        assertNull(person2FromTxPersistenceContext);

        person1.setId(null);
        person2.setId(null);
        person2.setUsername("beken_copy.fuht");
        person2.setHiringDate(LocalDateTime.now());
        exPcPersonService.insertWithoutTransaction(person1);
        exPcPersonService.insertWithTransaction(person2);

        person1FromTxPersistenceContext = txPcPersonService
                .find(person1.getId());
        assertNotNull(person1FromTxPersistenceContext);
        person2FromTxPersistenceContext = txPcPersonService
                .find(person2.getId());
        assertNotNull(person2FromTxPersistenceContext);
    }
}
