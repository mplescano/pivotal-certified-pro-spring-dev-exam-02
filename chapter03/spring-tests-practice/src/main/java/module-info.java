/**
 * Created by iuliana.cosmina on 21/01/19.
 */
module com.apress.cems.spring.tests.practice {
    requires com.apress.cems.dao;
    requires org.apache.commons.lang3;
    requires java.sql;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.jdbc;
    requires java.annotation;

    requires com.apress.cems.repos;
    requires  com.apress.cems.test.base;
}