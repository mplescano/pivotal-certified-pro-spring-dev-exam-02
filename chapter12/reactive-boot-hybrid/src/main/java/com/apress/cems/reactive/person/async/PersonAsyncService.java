package com.apress.cems.reactive.person.async;

import com.apress.cems.person.Person;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface PersonAsyncService {

    CompletableFuture<Person> findById(Long id);

    CompletableFuture<List<Person>> findAll();

    CompletableFuture<Person> save(Person person);

    CompletableFuture<Void> update(Long id, Person person);

    CompletableFuture<Void> delete(Long id);
}
