package com.assecor.data.dao;

import com.assecor.data.dao.util.CSVHelper;
import com.assecor.model.Person;
import com.assecor.model.csv.PersonCsv;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("daoCSV")
@Primary
@NoArgsConstructor
public class DaoPersonCSVImpl implements DaoEntity {

    @Override
    public void addPerson(Person person) {

        CSVHelper.addUser((PersonCsv) person);
    }

    @Override
    public List getPersonsByColor(String color) {

        return CSVHelper.filterByColor(color);
    }

    public Optional getPersonById(Integer id){

        return CSVHelper.filterById(id);
    }

    public List getPersons() {

        return CSVHelper.getUsers();
    }
}
