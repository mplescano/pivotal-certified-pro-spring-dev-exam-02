/*
Freeware License, some rights reserved

Copyright (c) 2019 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.cems.reactive.person.async;

import com.apress.cems.person.Person;
import com.apress.cems.reactive.person.PersonRepo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@Service
public class PersonAsyncServiceImpl implements PersonAsyncService {

    private final PersonRepo personRepo;

    public PersonAsyncServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    @Async
    public CompletableFuture<Person> findById(Long id) {
        try {
            return CompletableFuture.completedFuture(personRepo.findById(id).orElseThrow());
        } catch(Exception ex) {
            return CompletableFuture.failedFuture(ex);
        }
    }

    @Override
    @Async
    public CompletableFuture<List<Person>> findAll() {
        return CompletableFuture.completedFuture(personRepo.findAll());
    }

    @Override
    @Async
    public CompletableFuture<Person> save(Person person) {
        return CompletableFuture.completedFuture(personRepo.save(person));
    }

    @Override
    @Async
    public CompletableFuture<Void> update(Long id, Person updatedPerson) {
        Optional<Person> personOpt = personRepo.findById(id);
        if(personOpt.isPresent()) {
            Person original = personOpt.get();
            original.setUsername(updatedPerson.getUsername());
            original.setFirstName(updatedPerson.getFirstName());
            original.setLastName(updatedPerson.getLastName());
            personRepo.save(original);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<Void> delete(Long id) {
        personRepo.findById(id).ifPresent(personRepo::delete);
        return CompletableFuture.completedFuture(null);
    }
}
