package com.assecor.service;

import com.assecor.model.Person;
import com.assecor.repository.RepositoryPerson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private RepositoryPerson userFavoritColorRepo;

    public List<Person> getPersons() {

        return userFavoritColorRepo.getPersons();
    }

    public List<Person> getPersonsByColor(String color){

        return userFavoritColorRepo.getPersonsByColor(color);
    }

    public Optional<Person> getPersonById(Integer id){

        return userFavoritColorRepo.getPersonById(id);
    }

    public void addUser(Person person){

        userFavoritColorRepo.addPerson(person);
    }
}
