package com.assecor.repository;

import com.assecor.model.Person;

import java.util.List;
import java.util.Optional;

public interface RepositoryPerson {

    List<Person> getPersonsByColor(String color);
    List<Person> getPersons();
    Optional<Person> getPersonById(Integer id);
    void addPerson(Person person);
}