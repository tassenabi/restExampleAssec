package com.assecor.data.dao.util;

import com.assecor.model.Person;
import com.assecor.model.csv.PersonCsv;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperColor {

    private static Map<Integer, String> getMapOfColors(){

        Map<Integer, String> mapping = new HashMap<>();

        mapping.put(1, "blau");
        mapping.put(2, "grün");
        mapping.put(3, "violett");
        mapping.put(4, "rot");
        mapping.put(5, "gelb");
        mapping.put(6, "türkis");
        mapping.put(7, "weiß");

        return mapping;
    }

    public static PersonCsv convertColorStringToInt(PersonCsv person){

        person.setColor(getColorIntValueFromMap(person.getColor()));
        return person;
    }

    public static List<PersonCsv> convertColorIntToString(List<PersonCsv> listOfPersons){

       for (Person element : listOfPersons){
           String colorStringValue = getColorStringValueFromMap(Integer.parseInt(element.getColor().trim())).trim();
           element.setColor(colorStringValue);
       }
        return listOfPersons;
    }

    private static String getColorIntValueFromMap(String colorStringValue){

        String result = null;
        for(int key: getMapOfColors().keySet()) {
            if(getMapOfColors().get(key).equals(colorStringValue)) {
                result = String.valueOf(key);
            }
        }
        return result;
    }

    private static String getColorStringValueFromMap(Integer colorNumber){

        return getMapOfColors().get(colorNumber);
    }
}