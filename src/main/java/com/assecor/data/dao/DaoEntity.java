package com.assecor.data.dao;

import com.assecor.model.Person;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

public interface DaoEntity<T> {

    void addPerson(Person person);
    List<T> getPersonsByColor(String color);
    Optional<T> getPersonById(Integer id);
    List<T> getPersons();
}
