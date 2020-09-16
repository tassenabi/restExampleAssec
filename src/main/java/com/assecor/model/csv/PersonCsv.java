package com.assecor.model.csv;

import com.assecor.model.Person;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonCsv extends Person {

    private Integer id;
    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
    private String lastName;
    @CsvBindByPosition(position = 2)
    private String zipCode;
    @CsvBindByPosition(position = 2)
    private String street;
    @CsvBindByPosition(position = 3)
    private String color;

}

