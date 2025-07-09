package com.apress.cems.reactive.person.future;

import com.apress.cems.person.Person;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface PersonFutureService {

    Future<Person> findById(Long id);

    Future<List<Person>> findAll();

    Future<Person> save(Person person);

    Future<Void> update(Long id, Person person);

    Future<Void> delete(Long id);
}
