package com.assecor.assecorapp.restcontroller;

import com.assecor.model.Person;
import com.assecor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RestControllerPerson {

    @Autowired
    private final UserService userService;

    @RequestMapping(value={"/person/{id}"}, method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody Optional<Person> getUserById(@PathVariable int id){

        return userService.getPersonById(id);
    }

    @RequestMapping(value={"/persons/color/{color}"}, method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody List<Person> getUserByColor(@PathVariable String color){

        return userService.getPersonsByColor(color);
    }

    @RequestMapping(value={"/persons"}, method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody List<Person> getPersons(){

        return userService.getPersons();
    }

    @RequestMapping(value={"/persons"}, method = RequestMethod.POST,consumes = {"application/x-www-form-urlencoded"} )
    @ResponseBody
    public ResponseEntity<Void> newPerson(Person newPerson, UriComponentsBuilder ucBuilder){

        userService.addUser(newPerson);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(newPerson.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
