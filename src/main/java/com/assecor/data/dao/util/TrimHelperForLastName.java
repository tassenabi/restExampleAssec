package com.assecor.data.dao.util;

import com.assecor.model.csv.PersonCsv;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrimHelperForLastName {

    public static List<PersonCsv> trimFirstNameField(List<PersonCsv> personCsvList) {

        for (PersonCsv element : personCsvList){

            if(element.getLastName() !=null){
                String trimmedLastName = element.getLastName().trim();
                element.setLastName(trimmedLastName);
            }
        }

        return personCsvList;
    }
}
