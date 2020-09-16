package com.assecor.data.dao.util;

import com.assecor.model.csv.PersonCsv;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CSVHelper {

    private static final String DOCUMENTS_PATH = System.getProperty("user.home") + "/IdeaProjects/assecorrestapp/src/main/resources";
    private static final Path DOCUMENT_DIRECTORY = Paths.get(DOCUMENTS_PATH);
    private static final String FILE_NAME = "sample-input.csv";
    private static final String CSV_PATH = DOCUMENT_DIRECTORY.resolve(FILE_NAME).toAbsolutePath().toString();
    private static final File CSV_FILE = new File(CSV_PATH);

    public static List<PersonCsv> getUsers(){

        return loadCSVFileByBeans();

    }

    public static void addUser(PersonCsv person){

        List<PersonCsv> listToFilter = loadCSVFileByBeans();
        Integer newId = getIdForNewEntry(listToFilter);

        if (CSV_FILE.exists()) {
            try {
                Writer writer  = new FileWriter(CSV_PATH, true);

                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withApplyQuotesToAll(false)
                        .build();

                person = MapperColor.convertColorStringToInt(person);
                beanToCsv.write(person);
                writer.close();

            } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                System.out.println("There is a problem with the source .csv file");
                e.printStackTrace();
            }
        } else {
            try {
                Writer writer  = new FileWriter(CSV_PATH);

                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withApplyQuotesToAll(false)
                        .build();

                beanToCsv.write(person);
                writer.close();
            } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                System.out.println("There is a problem with the source .csv file");
                e.printStackTrace();
            }
        }
    }

    public static Optional filterById(Integer id){

        List<PersonCsv> listToFilter = loadCSVFileByBeans();

        return listToFilter.stream()
                .filter(userId -> userId.getId() == id)
                .findFirst();
    }

    public static List<PersonCsv> filterByColor(String color) {

        List<PersonCsv> listToFilter = loadCSVFileByBeans();

        return listToFilter.stream()
                .filter(col -> col.getColor() != null)
                .filter(col -> col.getColor().equals(color))
                .collect(Collectors.toList());
    }

    private static void setIdAutoincrement(List<PersonCsv> personCsvList) {

        int numberOfElements = personCsvList.size();

        IntStream.range(0, numberOfElements).forEach(i -> personCsvList.get(i).setId(i + 1));
    }

    private static Integer getIdForNewEntry(List<PersonCsv> personCsvList){

        return personCsvList.size() +1;
    }

    private static List<PersonCsv> loadCSVFileByBeans(){

        List<PersonCsv> personCsvList;
        FileReader fileReader = null;
        CsvToBean csvToBean = null;

        ColumnPositionMappingStrategy mappingStrategy = setColumMapping();

        try {

            fileReader = new FileReader(CSV_PATH);

            csvToBean = new CsvToBeanBuilder<PersonCsv>(fileReader)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withType(PersonCsv.class)
                    .withVerifyReader(true)
                    .withMappingStrategy(mappingStrategy)
                    .build();

        } catch (FileNotFoundException e) {
            System.out.println("There is a problem with the source .csv file");
            e.printStackTrace();
        }

        personCsvList = csvToBean.parse();

        setIdAutoincrement(personCsvList);
        personCsvList = MapperColor.convertColorIntToString(personCsvList);
        personCsvList = AdressHelper.splitAdressField(personCsvList);
        personCsvList = TrimHelperForLastName.trimFirstNameField(personCsvList);

        return personCsvList;
    }

    private static ColumnPositionMappingStrategy setColumMapping() {

        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(PersonCsv.class);
        String[] columns = new String[] {"id", "firstName", "lastName", "zipCode", "Street", "color"};
        strategy.setColumnMapping(columns);

        return strategy;
    }
}