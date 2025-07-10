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
package com.apress.cems.reactive.person;

import com.apress.cems.person.Person;
import com.apress.cems.reactive.person.async.PersonAsyncService;
import com.apress.cems.reactive.person.async.PersonAsyncServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@RestController
@RequestMapping("/persons-async")
public class PersonAsyncController {

    final PersonAsyncService asyncService;

    public PersonAsyncController(PersonAsyncService asyncService) {
        this.asyncService = asyncService;
    }

    // test with: curl -H "text/event-stream" http://localhost:8081/persons/
    @GetMapping//(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Future<List<Person>> persons() {
        return asyncService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<Person> show(@PathVariable Long id) {
        return asyncService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<Person> save(@RequestBody Person person){
        return asyncService.save(person);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Future<Void> update(@PathVariable Long id, Person person) {
        return asyncService.update(id, person);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Future<Void> delete(@PathVariable Long id) {
        return asyncService.delete(id);
    }
}
