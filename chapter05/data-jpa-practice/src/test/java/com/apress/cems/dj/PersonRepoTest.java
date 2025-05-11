package com.apress.cems.dj;

import com.apress.cems.dao.Person;
import com.apress.cems.dj.config.DataSourceConfig;
import com.apress.cems.dj.repos.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class, AppConfig.class})
public class PersonRepoTest {

    @Autowired
    PersonRepo personRepo;

    @Test
    void testRepeatedEntity() {
        Person person = new Person();
        person.setUsername("sherlock.holmes");
        person.setFirstName("Sherlock");
        person.setLastName("Holmes");
        person.setPassword("dudu");
        person.setHiringDate(LocalDateTime.now());

        assertThrows(DataIntegrityViolationException.class, () -> personRepo.save(person));
    }
}
