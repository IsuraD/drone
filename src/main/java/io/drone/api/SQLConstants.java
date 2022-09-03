package io.drone.api;

public class SQLConstants {
    public static final String ADD_DRONE_TABLE = "CREATE TABLE IF NOT EXISTS DRONE (\n" +
            "            SERIAL_NUMBER VARCHAR (50) NOT NULL,\n" +
            "            MODEL VARCHAR (50),\n" +
            "            WEIGHT_LIMIT INTEGER,\n" +
            "            BATTERY_CAPACITY INTEGER,\n" +
            "            STATE VARCHAR (50),\n" +
            "            PRIMARY KEY (SERIAL_NUMBER))";

    public static final String ADD_MEDICINE_TABLE = "CREATE TABLE IF NOT EXISTS MEDICINE (\n" +
            "            NAME VARCHAR (50) NOT NULL,\n" +
            "            WEIGHT INTEGER,\n" +
            "            CODE VARCHAR (50),\n" +
            "            IMAGE_URL VARCHAR (50),\n" +
            "            PRIMARY KEY (NAME))";

    public static final String ADD_DRONE_MEDICINE_ASSOC_TABLE = "CREATE TABLE IF NOT EXISTS DRONE_MEDICINE_ASSOCIATION (\n" +
            "            DRONE_SERIAL_NUMBER VARCHAR (50) NOT NULL,\n" +
            "            MEDICINE_NAME VARCHAR (50) NOT NULL,\n" +
            "            MEDICINE_COUNT INTEGER,\n" +
            "            FOREIGN KEY (DRONE_SERIAL_NUMBER) REFERENCES DRONE(SERIAL_NUMBER) ON DELETE CASCADE,\n" +
            "            FOREIGN KEY (MEDICINE_NAME) REFERENCES MEDICINE(NAME) ON DELETE CASCADE,\n" +
            "            UNIQUE (DRONE_SERIAL_NUMBER, MEDICINE_NAME))";

    public static final String ADD_DRONE = "INSERT INTO DRONE "
            + "(SERIAL_NUMBER, MODEL, WEIGHT_LIMIT, BATTERY_CAPACITY, STATE)"
            + "VALUES (?,?,?,?,?)";

    public static final String ADD_DRONE_MEDICINE = "INSERT INTO DRONE_MEDICINE_ASSOCIATION "
            + "(DRONE_SERIAL_NUMBER, MEDICINE_NAME, MEDICINE_COUNT)"
            + "VALUES (?,?,?)";

    public static final String LOAD_DRONE_MEDICINE = "SELECT MEDICINE_NAME, MEDICINE_COUNT FROM DRONE_MEDICINE_ASSOCIATION " +
            "WHERE DRONE_SERIAL_NUMBER = ?";

    public static final String LOAD_MEDICINE = "SELECT NAME,WEIGHT,CODE,IMAGE_URL FROM MEDICINE " +
            "WHERE NAME = ?";

    public static final String ADD_MEDICINE = "INSERT INTO MEDICINE "
            + "(NAME, WEIGHT, CODE, IMAGE_URL)"
            + "VALUES (?,?,?,?)";

    public static final String LOAD_DRONES = "SELECT * FROM DRONE";

    public static final String LOAD_DRONE = "SELECT * FROM DRONE WHERE SERIAL_NUMBER = ?";

}
