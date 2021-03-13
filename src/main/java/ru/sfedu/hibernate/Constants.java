package ru.sfedu.hibernate;

public class Constants {
    public static final String CONFIG_PATH = "config.path";
    public static final String DEFAULT_HIBERNATE_PATH = "hibernate_cfg";
    public static final String SQL_ALL_SCHEMAS = "SELECT schema_name FROM information_schema.schemata";
    public static final String SQL_TABLES="SELECT TABLE_NAME FROM information_schema.TABLES";
    public static final String SQL_USERS = "SELECT NAME FROM information_schema.USERS";
    public static final String SQL_COLUMN_OF_TABLE = "SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME = '%s'";
    public static final String HQL_READ_FROM = "from %s";
    public static final String HQL_DELETE_FROM = "delete from %s";
    public static final String SQL_SELECT_BY_ID = "select * from %s where id = %d";
}
