/**
 * @author Iuliana Cosmina
 * @since 1.0
 */

module com.apress.cems.sec {
    requires com.apress.cems.dao;
    requires com.apress.cems.sec.dj;

    requires java.naming;

    requires spring.core;
    requires spring.webmvc;
    requires spring.context;
    requires spring.web;

    requires javax.servlet.api;
    requires org.slf4j;
    requires java.annotation;
    requires com.zaxxer.hikari;
    requires spring.beans;
    requires spring.security.crypto;
    requires java.sql;

    requires spring.security.core;
    requires spring.security.web;
    requires spring.security.config;

    exports com.apress.cems.sec.config;
    exports com.apress.cems.sec.controllers;
    exports com.apress.cems.sec.problem;
    opens com.apress.cems.sec.config to spring.core;
}