package com.apress.cems.reactive.person.legacy;

import com.apress.cems.person.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface PersonLegacyService {

    Person findById(Long id);

    List<Person> findAll();

    Person save(Person person);

    void update(Long id, Person person);

    void delete(Long id);
}
