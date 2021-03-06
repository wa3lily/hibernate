package ru.sfedu.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.hibernate.Constants;
import ru.sfedu.hibernate.lab2.model.TestEntity;
import ru.sfedu.hibernate.lab3.MappedSuperclass.model.*;

import java.io.File;
import java.io.IOException;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    /**
     * Создание фабрики
     *
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration;
            try {
                File file  = new File(ConfigurationUtil.getConfigurationEntry(Constants.DEFAULT_HIBERNATE_PATH));
                configuration = new Configuration().configure(file);
            }catch (IOException | ConfigurationException e){
                configuration = new Configuration().configure();
            }
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            addEntities(metadataSources);
            //metadataSources.addAnnotatedClass(TestEntity.class);// Аннотированная сущность
            //metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }

    public static void addEntities(MetadataSources metadataSources ) {
        metadataSources.addAnnotatedClass(TestEntity.class);
        metadataSources.addAnnotatedClass(Author.class);
        metadataSources.addAnnotatedClass(Employee.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.TablePerClass.model.Author.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.TablePerClass.model.Employee.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.TablePerClass.model.People.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.SingleTable.model.Author.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.SingleTable.model.Employee.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.SingleTable.model.People.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.JoinedTable.model.Author.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.JoinedTable.model.Employee.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab3.JoinedTable.model.People.class);

        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab4.model.set.PriceParameters.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab4.model.list.PriceParametersList.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab4.model.map.PriceParametersMap.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab4.model.collection.PriceParametersCollection.class);
        metadataSources.addAnnotatedClass(ru.sfedu.hibernate.lab4.model.collection.CoverPrice.class);
    }
}

