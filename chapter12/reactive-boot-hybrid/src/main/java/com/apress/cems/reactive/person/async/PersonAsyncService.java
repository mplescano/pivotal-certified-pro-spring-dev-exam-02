package com.apress.cems.reactive.person.async;

import com.apress.cems.person.Person;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface PersonAsyncService {

    Future<Person> findById(Long id);

    Future<List<Person>> findAll();

    Future<Person> save(Person person);

    void update(Long id, Person person);

    void delete(Long id);
}
