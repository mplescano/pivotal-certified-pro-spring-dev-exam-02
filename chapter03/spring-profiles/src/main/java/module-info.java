/**
 * Created by iuliana.cosmina on 21/01/19.
 */
module com.apress.cems.spring.profiles.tests {
    requires com.apress.cems.dao;
    requires org.apache.commons.lang3;
    requires java.sql;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires java.annotation;
    requires spring.jdbc;
    requires com.oracle.database.jdbc;
    requires java.naming;
    requires com.apress.cems.repos;

    //exports com.apress.cems.jupiter.cfg.repos;
}