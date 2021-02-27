package ru.sfedu.hibernate.lab2.providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.sfedu.hibernate.Constants;
import ru.sfedu.hibernate.lab2.model.TestEntity;
import ru.sfedu.hibernate.utils.HibernateUtil;
import java.util.List;
import java.util.Optional;

public class TestEntityProvider{

    private static Logger log = LogManager.getLogger(TestEntityProvider.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public Long save(TestEntity entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    public Optional<TestEntity> getById(Class<TestEntity> entity, Long id) {
        Session session = this.getSession();
        TestEntity testEntity = session.get(entity, id);
        session.close();
        try{
            return Optional.of(testEntity);
        }catch (NullPointerException e){
            return Optional.empty();
        }
    }

    public Boolean update(TestEntity entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
        return true;
    }


    public List<TestEntity> read(Class<TestEntity> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(String.format(Constants.HQL_READ_FROM,entity.getSimpleName()));
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public Boolean delete(Class<TestEntity> entity, Long id) {
        Boolean result = false;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TestEntity testEntity = session.get(entity, id);
            session.delete(testEntity);
            result = true;
        }catch (NullPointerException e){
            log.error(e);
            log.debug("There is not id = "+id);
        }finally {
            transaction.commit();
            session.close();
            return result;
        }
    }

//    public Boolean deleteEntity(TestEntity entity) {
//        Boolean result = false;
//        Session session = this.getSession();
//        Transaction transaction = session.beginTransaction();
//        if (session.get(TestEntity.class, entity.getId()) != null){
//            session.delete(entity);
//            result = true;
//        }else{
//            log.debug("There is not such entity: "+entity);
//        }
//        transaction.commit();
//        session.close();
//        return result;
//    }

    public void truncateTable(Class<TestEntity> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(String.format(Constants.HQL_DELETE_FROM,entity.getSimpleName()));
        query.executeUpdate();
        transaction.commit();
        session.close();
        return ;
    }
}
