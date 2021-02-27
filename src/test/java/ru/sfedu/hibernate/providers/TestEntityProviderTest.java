package ru.sfedu.hibernate.providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.hibernate.Constants;
import ru.sfedu.hibernate.lab2.model.TestEntity;
import ru.sfedu.hibernate.lab2.providers.TestEntityProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestEntityProviderTest {

    private static Logger log = LogManager.getLogger(HibernateMetadataProviderTest.class);
    TestEntityProvider instance = new TestEntityProvider();

    @Test
    public void TestSave() {
        log.info("save");
        TestEntity entity = new TestEntity();
        entity.setName("Name1");
        entity.setDescription("It is the first entity");
        entity.setDateCreated(new Date(2020,02,18));
        entity.setCheck(true);
        Long result = instance.save(entity);
        Optional<TestEntity> entity1 = instance.getById(TestEntity.class, result);
        log.debug(entity1.get());
        assertEquals(entity, entity1.get());
    }

    @Test
    public void TestGetByIdFail() {
        log.info("GetByIdFail");
        Optional<TestEntity> entity1 = instance.getById(TestEntity.class, 200L);
        assertNull(entity1.orElse(null));
    }

    @Test
    public void TestDeleteSuccess() {
        log.info("DeleteSuccess");
        TestEntity entity = new TestEntity();
        entity.setName("Name1");
        entity.setDescription("It is the first entity");
        entity.setDateCreated(new Date(2020,02,18));
        entity.setCheck(true);
        Long result = instance.save(entity);
        Optional<TestEntity> entity1 = instance.getById(TestEntity.class, result);
        log.debug(entity1.get());
        assertEquals(entity, entity1.get());
        log.debug(instance.delete(TestEntity.class,result));
        entity1 = instance.getById(TestEntity.class, result);
        log.debug(entity1.orElse(null));
        assertNull(entity1.orElse(null));
    }

    @Test
    public void TestDeleteFail() {
        log.info("DeleteFail");
        Long id = 100l;
        TestEntity entity = new TestEntity();
        entity.setId(id);
        entity.setName("Name1");
        entity.setDescription("It is the first entity");
        entity.setDateCreated(new Date(2020,02,18));
        entity.setCheck(true);
        log.debug(instance.delete(TestEntity.class,id));
        Optional <TestEntity> entity1 = instance.getById(TestEntity.class, id);
        log.debug(entity1.orElse(null));
        assertNull(entity1.orElse(null));
    }

//    @Test
//    public void TestDeleteEntitySuccess() {
//        log.info("DeleteEntitySuccess");
//        ITestEntityProvider instance = new TestEntityProvider();
//        TestEntity entity = new TestEntity();
//        entity.setName("Name4");
////        entity.setDescription("It is the first entity");
////        entity.setDateCreated(new Date(2020,02,18));
////        entity.setCheck(true);
//        Long result = instance.save(entity);
//        log.debug("id = "+result);
//        Optional<TestEntity> entity1 = instance.getById(TestEntity.class, result);
//        log.debug(entity1.get());
//        assertEquals(entity, entity1.get());
//        instance.deleteEntity(entity);
//        entity1 = instance.getById(TestEntity.class, result);
//        log.debug(entity1.orElse(null));
//        assertNull(entity1.orElse(null));
//    }
//
//    @Test
//    public void TestDeleteEntityFail() {
//        log.info("DeleteEntityFail");
//        ITestEntityProvider instance = new TestEntityProvider();
//        Long id = 100l;
//        TestEntity entity = new TestEntity();
//        entity.setId(id);
//        entity.setName("Name1");
////        entity.setDescription("It is the first entity");
////        entity.setDateCreated(new Date(2020,02,18));
////        entity.setCheck(true);
//        log.debug(instance.deleteEntity(entity));
//        Optional <TestEntity> entity1 = instance.getById(TestEntity.class, id);
//        log.debug(entity1.orElse(null));
//        assertNull(entity1.orElse(null));
//    }

    @Test
    public void TestUpdateSuccess(){
        log.info("UpdateSuccess");
        TestEntity entity = new TestEntity();
        entity.setName("Name1");
        entity.setDescription("It is the first entity");
        entity.setDateCreated(new Date(2020,02,18));
        entity.setCheck(true);
        Long result = instance.save(entity);
        TestEntity entity1 = instance.getById(TestEntity.class, result).get();
        assertEquals(entity, entity1);
        entity.setName("NewName");
        log.debug("entity"+entity);
        log.debug(instance.update(entity));
        entity1 = instance.getById(TestEntity.class, result).get();
        log.debug(entity1);
        assertEquals(entity, entity1);
    }

    @Test
    public void TestUpdateFail(){
        log.info("UpdateFail");
        TestEntity entity = new TestEntity();
        Long id = 101L;
        entity.setId(id);
        entity.setName("Name1");
        entity.setDescription("It is the first entity");
        entity.setDateCreated(new Date(2020,02,18));
        entity.setCheck(true);
        assertNull(instance.getById(TestEntity.class, id).orElse(null));
        instance.update(entity);
        assertNull(instance.getById(TestEntity.class, id).orElse(null));
    }

    @Test
    public void TestReadSuccess(){
        log.info("ReadSuccess");
        instance.truncateTable(TestEntity.class);
        TestEntity entity = new TestEntity();
        entity.setName("Name1");
        entity.setDescription("It is the first entity");
        entity.setDateCreated(new Date(2020,02,18));
        entity.setCheck(true);
        Long result = instance.save(entity);
        TestEntity entity1 = instance.getById(TestEntity.class, result).get();
        List<TestEntity> list = new ArrayList<>();
        list.add(entity1);
        List<TestEntity> list2 = instance.read(TestEntity.class);
        log.debug(list2);
        assertEquals(list, list2);
    }

    @Test
    public void TestReadFail(){
        log.info("ReadFail");
        instance.truncateTable(TestEntity.class);
        List<TestEntity> list2 = instance.read(TestEntity.class);
        log.debug(list2);
        assertTrue(list2.isEmpty());
    }
}