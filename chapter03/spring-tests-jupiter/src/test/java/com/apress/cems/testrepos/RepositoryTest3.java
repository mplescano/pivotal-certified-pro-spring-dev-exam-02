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
package com.apress.cems.testrepos;

import com.apress.cems.dao.Person;
import com.apress.cems.repos.PersonRepo;
import com.apress.cems.repos.impl.JdbcPersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@SpringJUnitConfig(classes = RepositoryTest3.TestCtxConfig.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class RepositoryTest3 {

    static final Long PERSON_ID = 1L;

    @Autowired
    PersonRepo personRepo;

    @BeforeEach
    void setUp() {
        assertNotNull(personRepo);
    }

    @Test
    //@Sql({"classpath:db/test-data-one.sql"})
     @Sql(
            scripts = "classpath:db/test-data-one.sql",
            config = @SqlConfig(commentPrefix = "`", separator = "@@")
    )
    void testFindByIdPositive() {
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions:: fail
        );
    }

    @Test
    //@Sql({"classpath:db/test-data-two.sql"})
    @Sql(
        statements = {
            "INSERT INTO PERSON(ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE, VERSION, CREATED_AT, MODIFIED_AT) VALUES (2, 'irene.adler', 'Irene', 'Adler', 'id123ds', '1990-08-18 00:03', 1, '1990-07-18 00:04', '1990-07-18 00:05');"
        })
    void testFindByComplete() {
        var personOpt = personRepo.findByCompleteName("Irene", "Adler");
        personOpt.ifPresent(p ->
                assertAll(
                        () -> assertEquals("Irene", p.getFirstName()),
                        () -> assertEquals("Adler", p.getLastName())
                )

        );
    }

    @Configuration
    static class TestCtxConfig {
        @Bean
        PersonRepo jdbcPersonRepo() {
            return new JdbcPersonRepo(jdbcTemplate());
        }

        @Bean
        JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

        @Bean
        public DataSource dataSource() {
            var builder = new EmbeddedDatabaseBuilder();
            var db = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .generateUniqueName(true)
                    .addScript("db/schema.sql")
                    .build();
            return db;
        }
    }

}
