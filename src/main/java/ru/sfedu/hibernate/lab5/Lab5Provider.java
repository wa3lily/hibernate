package ru.sfedu.hibernate.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.sfedu.hibernate.Constants;
import ru.sfedu.hibernate.utils.HibernateUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class Lab5Provider {
    private static Logger log = LogManager.getLogger(Lab5Provider.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public <T> Long save(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    public <T> Optional<T> getById(Class<T> entityClass, Long id) {
        Session session = this.getSession();
        T entity = session.get(entityClass, id);
        session.close();
        try{
            return Optional.of(entity);
        }catch (NullPointerException e){
            return Optional.empty();
        }
    }

    public <T> Boolean update(T entity) {
        Boolean result = false;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(entity);
            result = true;
        }catch (EntityNotFoundException e){
            log.error(e);
        }finally {
            transaction.commit();
            session.close();
            return result;
        }
    }


    public <T> List<T> read(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(String.format(Constants.HQL_READ_FROM,entity.getSimpleName()));
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public <T> Boolean delete(Class<T> entityClass, Long id) {
        Boolean result = false;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            T entity = session.get(entityClass, id);
            session.delete(entity);
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

    public <T> void truncateTable(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(String.format(Constants.HQL_DELETE_FROM,entity.getSimpleName()));
        query.executeUpdate();
        transaction.commit();
        session.close();
        return ;
    }

    public <T> void deleteTable(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity.getSimpleName());
        transaction.commit();
        session.close();
        return ;
    }
}
