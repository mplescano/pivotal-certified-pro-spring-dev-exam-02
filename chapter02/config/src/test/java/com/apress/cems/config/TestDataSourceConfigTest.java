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
package com.apress.cems.config;

import com.apress.cems.pojos.repos.DetectiveRepo;
import com.apress.cems.pojos.repos.EvidenceRepo;
import com.apress.cems.repos.JdbcDetectiveRepo;
import com.apress.cems.repos.JdbcEvidenceRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@Slf4j
class TestDataSourceConfigTest {

    @Test
    void testMultipleCfgSource() {
        var ctx =
                new AnnotationConfigApplicationContext(TestDataSourceConfig.class, RepositoryConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            log.info("Bean " + beanName + " of type "
                    + ctx.getBean(beanName).getClass().getSimpleName());
        }

        var evidenceRepo = ctx.getBean(JdbcEvidenceRepo.class);
        var detectiveRepo = ctx.getBean(JdbcDetectiveRepo.class);

        assertNotNull(evidenceRepo);
        assertNotNull(detectiveRepo);

        var dataSource = ctx.getBean("two", DataSource.class);
        assertNotNull(dataSource);
    }
}