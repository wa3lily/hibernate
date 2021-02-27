package ru.sfedu.hibernate.providers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateMetadataProviderTest {

    private static Logger log = LogManager.getLogger(HibernateMetadataProviderTest.class);
    HibernateMetadataProvider instance = new HibernateMetadataProvider();

    @Test
    public void testGetAllSchemas(){
        log.info("getAllSchemas");
        List result = instance.getAllSchemas();
        assertNotNull(result);
    }

    @Test
    public void testGetListSchemas(){
        log.info("getListSchemas");
        List result = instance.getListSchemas();
        assertNotNull(result);
    }

    @Test
    public void testGetListTables(){
        log.info("getListTables");
        List result = instance.getListTables();
        assertNotNull(result);
    }

    @Test
    public void testGetListUsers(){
        log.info("getListUsers");
        List result = instance.getListUsers();
        assertNotNull(result);
    }

    @Test
    public void testGetListColumnOfTable(){
        log.info("getListColumnOfTable");
        List result = instance.getListColumnOfTable("USERS");
        assertNotNull(result);
    }


}