/**
 * Created by iuliana.cosmina on 21/01/19.
 */
module com.apress.cems.test.base {
    requires com.apress.cems.dao;
    requires com.apress.cems.repos;
    requires org.apache.commons.lang3;
    requires java.sql;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires java.annotation;

    exports com.apress.cems.services.impl;
    exports com.apress.cems.stub.repo;
    exports com.apress.cems.stub.util;
}