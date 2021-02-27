package ru.sfedu.hibernate.providers;

import ru.sfedu.hibernate.Constants;

import java.util.List;

public interface IMetadataProvider {
    List getAllSchemas();

    List getListSchemas();

    List getListTables() ;

    List getListUsers();

    List getListColumnOfTable(String table_name);
}
