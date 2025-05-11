/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
module com.apress.cems.entities {
    requires java.persistence;
    requires spring.context;
    requires java.validation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    exports com.apress.cems.base;
    exports com.apress.cems.person;
    exports com.apress.cems.detective;
}