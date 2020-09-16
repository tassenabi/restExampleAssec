package com.assecor.repository;

import com.assecor.data.dao.DaoEntity;
import com.assecor.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RepositoryPersonImpl implements RepositoryPerson {

    @Qualifier("daoCSV")
    @Autowired
    private final DaoEntity<Person> daoUser;

    @Override
    public List<Person> getPersonsByColor(String color) {

        return daoUser.getPersonsByColor(color);
    }

    @Override
    public List<Person> getPersons() {

        return daoUser.getPersons();
    }

    @Override
    public Optional<Person> getPersonById(Integer id) {

        return daoUser.getPersonById(id);
    }

    @Override
    public void addPerson(Person person) {

        daoUser.addPerson(person);
    }
}