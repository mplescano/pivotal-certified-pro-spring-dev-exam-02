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
package com.apress.cems.reactive.person.legacy;

import com.apress.cems.person.Person;
import com.apress.cems.reactive.person.PersonRepo;
import com.apress.cems.reactive.person.reactor.PersonReactiveService;
import com.apress.cems.util.NumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@Service
public class PersonLegacyServiceImpl implements PersonLegacyService {

    private PersonRepo personRepo;

    public PersonLegacyServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Person findById(Long id) {
        return personRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Person save(Person person) {
        if(StringUtils.isEmpty(person.getPassword())){
            person.setPassword(NumberGenerator.getPassword());
        }
        return personRepo.save(person);
    }

    @Override
    public void update(Long id, Person updatedPerson) {
        Optional<Person> personOpt = personRepo.findById(id);
        if(personOpt.isPresent()) {
            Person original = personOpt.get();
            original.setUsername(updatedPerson.getUsername());
            original.setFirstName(updatedPerson.getFirstName());
            original.setLastName(updatedPerson.getLastName());
            personRepo.save(original);
        }
    }

    @Override
    public void delete(Long id) {
        personRepo.findById(id).ifPresent(person -> personRepo.delete(person));
    }
}
