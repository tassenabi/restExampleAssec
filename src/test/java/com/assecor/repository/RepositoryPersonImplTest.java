package com.assecor.repository;

import com.assecor.data.dao.DaoEntity;
import com.assecor.data.dao.DaoPersonCSVImpl;
import com.assecor.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryPersonImplTest {

    private DaoEntity daoPerson;
    private RepositoryPerson repositoryPerson;

    @BeforeEach
    void setUp() {

        daoPerson = new DaoPersonCSVImpl();
        repositoryPerson = new RepositoryPersonImpl(daoPerson);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPersonsByColor() {

        //Assert
        int expectedCountOfPersonsWithBlueColor = 2;

        //Act
        List<Person> person = repositoryPerson.getPersonsByColor("blau");
        int actualCountOfPersonsWithBlueColor = person.size();

        //Arrange
        assertEquals(actualCountOfPersonsWithBlueColor, expectedCountOfPersonsWithBlueColor);
    }

    @Test
    void getPersons() {

        int actualnumberOfPersonsTotal = repositoryPerson.getPersons().size();
        int expectednumberOfPersonsTotal = 10;
        assertEquals(actualnumberOfPersonsTotal, expectednumberOfPersonsTotal);

    }

    @Test
    void getPersonById() {

        Optional<Person> actualPerson = repositoryPerson.getPersonById(10);
        String expectedPersonName = "Klaussen";
        assertEquals(expectedPersonName, actualPerson.get().getFirstName());

    }

    @Test
    void addPerson() {

    }
}