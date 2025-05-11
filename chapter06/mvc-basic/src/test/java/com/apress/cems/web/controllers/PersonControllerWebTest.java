package com.apress.cems.web.controllers;


import com.apress.cems.dao.Person;
import com.apress.cems.dj.services.DetectiveService;
import com.apress.cems.dj.services.PersonService;
import com.apress.cems.web.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringJUnitWebConfig(classes = WebConfig.class)
public class PersonControllerWebTest {

    @Configuration
    static class Config {

        @Bean
        public DetectiveService detectiveService() {
            return Mockito.mock(DetectiveService.class);
        }

        @Bean
        public PersonService personService() {
            return Mockito.mock(PersonService.class);
        }
    }

    @Autowired
    DetectiveService detectiveService;
    @Autowired
    PersonService personService;

    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        Mockito.reset(detectiveService, personService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testListHandler() throws Exception {
        List<Person> list = new ArrayList<>();
        Person p = new Person();
        p.setId(1L);
        list.add(p);
        when(personService.findAll()).thenReturn(list);

        mockMvc.perform(get("/persons/list"));
    }
}
