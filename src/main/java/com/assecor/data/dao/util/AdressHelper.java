package com.assecor.data.dao.util;

import com.assecor.model.csv.PersonCsv;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdressHelper {

    public static List<PersonCsv> splitAdressField(List<PersonCsv> personCsvList) {

        for (PersonCsv element : personCsvList){

            int numOfChar = element.getStreet().length();
            if(element.getStreet() !=null){

                String zipCode = element.getStreet().substring(0,6).trim();
                String street = element.getStreet().substring(6,numOfChar).trim();

                element.setZipCode(zipCode);
                element.setStreet(street);
            }
        }

        return personCsvList;
    }

}
