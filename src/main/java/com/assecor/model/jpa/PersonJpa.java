package com.assecor.model.jpa;

import com.assecor.model.Person;

import javax.persistence.*;
import java.io.Serializable;

import static com.assecor.data.databaseconfig.DatabaseProperties.*;

    @Entity
    @Table(name= TABLENAME)
    public class PersonJpa extends Person implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = ID, unique = true)
        private Integer id;

        @Column(name = FIRSTNAME, unique = false)
        private String firstName;

        @Column(name = LASTNAME, unique = false)
        private String lastName;

        @Column(name = ZIPCODE, unique = false)
        private String zipcode;

        @Column(name = STREET, unique = false)
        private String street;

        @Column(name = COLOR, unique = false)
        private String color;
    }