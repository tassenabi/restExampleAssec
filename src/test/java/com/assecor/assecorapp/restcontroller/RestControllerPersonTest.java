package com.assecor.assecorapp.restcontroller;

import com.assecor.model.Person;
import com.assecor.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestControllerPerson.class)
class RestControllerPersonTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    void getUserById_ShouldReturnCorrectPerson() throws Exception {

        Person alex = new Person(1, "alex", "Robert", "37235", "Köln", "blau");


        given(service.getPersonById(1)).willReturn(java.util.Optional.of(alex));


        this.mvc
                .perform(get("/person/{id}", 1))
                .andDo(print()).andExpect(status().isOk())

                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getUserByColor_ShouldReturnCorrectPerson() throws Exception {

        Person alex = new Person(1, "alex", "Robert", "37235", "Köln", "blau");

        List<Person> list = Arrays.asList(alex);

        given(service.getPersons()).willReturn(list);

        this.mvc.perform(get("/persons/color/{color}", alex))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].color", is("blau")));

    }

    @Test
    void getPersons_ShouldReturnListOfAllPersons() throws Exception  {

        //Arrange & Act
        Person alex = new Person(1, "alex", "Robert", "37235", "Köln", "blau");
        when(service.getPersons()).thenReturn(Arrays.asList(alex));

        //Assert
        this.mvc.perform(get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("alex")));
    }

}