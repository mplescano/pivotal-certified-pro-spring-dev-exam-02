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
package com.apress.cems.mock;

import com.apress.cems.dao.Person;
import com.apress.cems.repos.PersonRepo;
import com.apress.cems.repos.impl.JdbcPersonRepo;
import com.apress.cems.repos.util.PersonRowMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration
public class SpringUnitTest3 {

    public static final Long PERSON_ID = 1L;

    @Autowired
    @Qualifier("jdbcPersonRepo3")
    PersonRepo personRepo;

    // mocking the database
    @Autowired
    @Qualifier("jdbcTemplate3")
    JdbcTemplate jdbcTemplate;

    @Test
    public void testFindByIdPositive() {
        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(PersonRowMapper.class), anyLong())).thenReturn(new Person());

        assertTrue(personRepo.findById(PERSON_ID).isPresent());
    }

    @Configuration
    static class MockCtxConfig3 {
        @Bean
        JdbcTemplate jdbcTemplate3() {
            return mock(JdbcTemplate.class);
        }

        @Bean
        PersonRepo jdbcPersonRepo3() {
            return new JdbcPersonRepo(jdbcTemplate3());
        }

    }
}
