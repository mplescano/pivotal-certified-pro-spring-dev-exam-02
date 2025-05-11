package com.apress.cems.aop.service;

import com.apress.cems.dao.Person;
import com.apress.cems.repos.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BadPersonServiceImpl extends PersonServiceImpl {

    public BadPersonServiceImpl(PersonRepo personRepo) {
        super(personRepo);
    }

    @Override
    public Set<Person> findAll() {
        throw new RuntimeException("Error");
    }
}
