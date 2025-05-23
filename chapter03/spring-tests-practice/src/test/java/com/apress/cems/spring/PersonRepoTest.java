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
package com.apress.cems.spring;

import com.apress.cems.cfg.TestDbConfig;
import com.apress.cems.dao.Person;
import com.apress.cems.repos.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repos.ReposConfig;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
// TODO 18. Complete the test class definition in order for all the test to pass.
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDbConfig.class, ReposConfig.class})
public class PersonRepoTest {
    public static final Long PERSON_ID = 1L;

    @Autowired
    PersonRepo personRepo;

    public void setUp(){
        assertNotNull(personRepo);
    }

    @Test
    public void testFindByIdPositive(){
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions::fail
        );

    }

    public void testFindAll(){
        var personSet = personRepo.findAll();
        assertNotNull(personSet);
        assertEquals(2, personSet.size());
    }
}
