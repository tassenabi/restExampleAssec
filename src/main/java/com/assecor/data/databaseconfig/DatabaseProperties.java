package com.assecor.data.databaseconfig;

public class DatabaseProperties {

    //Also doable with a Properties, File, and a FileInput-Object
    public static final String TABLENAME ="User";
    public static final String ID = "user_id";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME ="lastName";
    public static final String ZIPCODE = "zipcode";
    public static final String STREET = "street";
    public static final String COLOR = "color";


    //Private Constructor because this is a utility class
    private DatabaseProperties(){
        throw new IllegalStateException("DatabaseProperties.class is a utility class and should not be an instance");
    }
}
