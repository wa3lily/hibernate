package ru.sfedu.hibernate.providers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.sfedu.hibernate.Constants;
import ru.sfedu.hibernate.utils.HibernateUtil;

public class HibernateMetadataProvider implements IMetadataProvider{

    private static Logger log = LogManager.getLogger(HibernateMetadataProvider.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public List getList(String str) {
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(str);
        List resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List getAllSchemas() {
        List resultList = getList(Constants.SQL_ALL_SCHEMAS);
        log.info("Get schemas size: {}", resultList != null ? resultList.size() : null);
        return resultList;
    }

    @Override
    public List getListSchemas() {
        List resultList = getList(Constants.SQL_ALL_SCHEMAS);
        log.info("Get list schemas: {}", resultList);
        return resultList;
    }

    @Override
    public List getListTables() {
        List resultList = getList(Constants.SQL_TABLES);
        log.info("Get list tables: {}", resultList);
        return resultList;
    }

    @Override
    public List getListUsers() {
        List resultList = getList(Constants.SQL_USERS);
        log.info("Get list users: {}", resultList);
        return resultList;
    }

    @Override
    public List getListColumnOfTable(String table_name) {
        List resultList = getList(String.format(Constants.SQL_COLUMN_OF_TABLE, table_name));
        log.info("Get list column of table {}: {}", table_name, resultList);
        return resultList;
    }
}
