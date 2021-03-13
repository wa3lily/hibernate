package ru.sfedu.hibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.enums.EmployeeType;
import ru.sfedu.hibernate.lab2.model.TestEntity;
import ru.sfedu.hibernate.lab2.providers.TestEntityProvider;
import ru.sfedu.hibernate.lab3.JoinedTable.model.People;
import ru.sfedu.hibernate.lab3.Lab3Provider;
import ru.sfedu.hibernate.lab3.MappedSuperclass.model.Employee;
import ru.sfedu.hibernate.lab3.TablePerClass.model.Author;
import ru.sfedu.hibernate.lab4.Lab4Provider;
import ru.sfedu.hibernate.lab4.model.collection.CoverPrice;
import ru.sfedu.hibernate.lab4.model.collection.PriceParametersCollection;
import ru.sfedu.hibernate.lab4.model.list.PriceParametersList;
import ru.sfedu.hibernate.lab4.model.map.PriceParametersMap;
import ru.sfedu.hibernate.lab4.model.set.PriceParameters;
import ru.sfedu.hibernate.lab5.Lab5Provider;
import ru.sfedu.hibernate.lab5.model.many_to_one.Author5;
import ru.sfedu.hibernate.lab5.model.many_to_one.Book5;
import ru.sfedu.hibernate.providers.HibernateMetadataProvider;

import java.util.*;

public class Main {
        private static final Logger log = LogManager.getLogger(Main.class);
        public static void main(String[] args) {
            if (args.length >= 2) {
                System.out.println(task(args));
                System.exit(0);
            } else {
                log.error("Not enough parameters");
            }
        }

        public static String task(String[] s) {
            try {
                switch (s[0].toUpperCase()) {
                    case Constants.TASK_1:
                        return task1(s);
                    case Constants.TASK_2:
                        return task2(s);
                    case Constants.TASK_3:
                        return task3(s);
                    case Constants.TASK_4:
                        return task4(s);
                    case Constants.TASK_5:
                        return task5(s);
                }
            } catch (Exception exception) {
                log.error(exception);
            }
            return null;
        }

        public static String task1(String[] s){
            try {
                HibernateMetadataProvider instance = new HibernateMetadataProvider();
                switch (s[1]) {
                    case "1":
                        return instance.getAllSchemas().toString();
                    case "2":
                        return instance.getListSchemas().toString();
                    case "3":
                        return instance.getListTables().toString();
                    case "4":
                        return instance.getListUsers().toString();
                }
            }catch(Exception e){
                log.error(e);
                return "Fail";
            }
            return null;
        }

        public static String task2(String[] s){
            try {
                TestEntityProvider instance= new TestEntityProvider();

//Данные из тестов

                switch (s[1].toLowerCase()) {
                    case "save":
                        TestEntity testEntity = new TestEntity();
                        testEntity.setName(s[2]);
                        testEntity.setDescription(s[3]);
                        instance.save(testEntity);
                        return instance.getById(TestEntity.class,testEntity.getId()).toString();
                    case "del":
                        TestEntity testDelEntity = new TestEntity();
                        testDelEntity.setName(s[2]);
                        testDelEntity.setDescription(s[3]);
                        instance.save(testDelEntity);
                        System.out.println(testDelEntity);
                        instance.delete(TestEntity.class,testDelEntity.getId());
                        return instance.getById(TestEntity.class,testDelEntity.getId()).toString();
                    case "get":
                        TestEntity getEntity = new TestEntity();
                        getEntity.setName("it was custom name");
                        getEntity.setDescription("it was custom description");
                        instance.save(getEntity);
                        return instance.getById(TestEntity.class,Long.parseLong(s[2])).toString();
                    case "upd":
                        TestEntity entity = new TestEntity();
                        entity.setName("it was custom name");
                        entity.setDescription("it was custom description");
                        instance.save(entity);
                        System.out.println(entity.toString());
                        entity.setName(s[2]);
                        instance.update(entity);
                        Optional<TestEntity> updTestEntity = instance.getById(TestEntity.class,entity.getId());
                        return updTestEntity.toString();
                }
            }catch(Exception e){
                log.error(e);
                return "Fail";
            }
            return null;
        }

        public static String task3(String[] s){
            Lab3Provider provider = new Lab3Provider();
            switch (s[1].toLowerCase()) {
                case "jt":
                    People people = new People();
                    people.setFirstName("User First Name");
                    people.setSecondName("User Second Name");
                    people.setLastName("User Last Name");
                    people.setPhone("User phone");
                    provider.save(people);
                    log.info(people.toString());
                    return provider.getById(People.class,Long.parseLong(s[2])).toString();
                case "ms":
                    Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
                    provider.save(employee);
                    return provider.getById(Employee.class,Long.parseLong(s[2])).toString();
                case "st":
                    ru.sfedu.hibernate.lab3.SingleTable.model.Author author1 = new ru.sfedu.hibernate.lab3.SingleTable.model.Author();
                    author1.setFirstName("User First Name");
                    author1.setSecondName("User Second Name");
                    author1.setLastName("User Last Name");
                    author1.setPhone("User phone");
                    provider.save(author1);
                    log.info(author1.getId());
                    return provider.getById(ru.sfedu.hibernate.lab3.SingleTable.model.Author.class,Long.parseLong(s[2])).toString();
                case "tpc":
                    Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
                    provider.save(author);
                    return provider.getById(Author.class,Long.parseLong(s[2])).toString();
            }
            return null;
        }

    public static String task4 (String[] s){
        try {
            Lab4Provider instance = new Lab4Provider();
            switch (s[1]) {
                case "collection":
                    Set<CoverPrice> map = new HashSet<>();
                    CoverPrice coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
                    CoverPrice coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
                    map.add(coverPrice);
                    map.add(coverPrice);
                    map.add(coverPrice2);
                    PriceParametersCollection priceParameters = createPriceParametersCollection( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
                    instance.save(priceParameters);
                    return instance.getById(PriceParametersCollection.class,Long.parseLong(s[2])).toString();
                case "list":
                    List<Long> list = new ArrayList<>();
                    list.add(2L);
                    list.add(2L);
                    list.add(4L);
                    PriceParametersList priceParametersList = createPriceParametersList( 13.4, list, 16.3, "2019-01-01", "2021-01-01");
                    instance.save(priceParametersList);
                    return instance.getById(PriceParametersList.class,Long.parseLong(s[2])).toString();
                case "map":
                    Map<Long, CoverType> map2 = new HashMap();
                    map2.put(2L, CoverType.RIGID_COVER);
                    map2.put(2L, CoverType.RIGID_COVER);
                    map2.put(4L, CoverType.PAPERBACK);
                    PriceParametersMap priceParametersMap = createPriceParametersMap( 13.4, map2, 16.3, "2019-01-01", "2021-01-01");
                    instance.save(priceParametersMap);
                    return instance.getById(PriceParametersMap.class,Long.parseLong(s[2])).toString();
                case "set":
                    Set<Long> map3 = new HashSet<>();
                    map3.add(2L);
                    map3.add(2L);
                    map3.add(4L);
                    PriceParameters priceParametersSet = createPriceParametersSet( 13.4, map3, 16.3, "2019-01-01", "2021-01-01");
                    Long resultA = instance.save(priceParametersSet);
                    return instance.getById(PriceParameters.class,Long.parseLong(s[2])).toString();
            }
        }catch(Exception e){
            log.error(e);
            return "Fail";
        }
        return null;
    }

    public static String task5 (String[] s){
        try {
            Lab5Provider provider = new Lab5Provider();
            Author5 author = createAuthor5("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
            Book5 book = createBook5(author,"Цифровая бухгалтерия",400);
            provider.save(author);
            provider.save(book);
            switch (s[1]) {
                case "criteria":
                    long start = System.currentTimeMillis();
                    System.out.println(provider.getByIdCriteria(Author5.class,Long.parseLong(s[2])).toString());
                    Thread.sleep(1000);
                    long finish = System.currentTimeMillis();
                    long elapsed = finish - start;
                    return " "+elapsed;
                case "hql":
                    long start1 = System.currentTimeMillis();
                    System.out.println(provider.getById(Author5.class,Long.parseLong(s[2])).toString());
                    Thread.sleep(1000);
                    long finish1 = System.currentTimeMillis();
                    long elapsed1 = finish1 - start1;
                    return " "+elapsed1;
                case "sql":
                    long start2 = System.currentTimeMillis();
                    System.out.println(provider.getByIdNativeSQL(Author5.class,Long.parseLong(s[2])).toString());
                    Thread.sleep(1000);
                    long finish2 = System.currentTimeMillis();
                    long elapsed2 = finish2 - start2;
                    return " "+elapsed2;

            }
        }catch(Exception e){
            log.error(e);
            return "Fail";
        }
        return null;
    }

        public static Employee createEmployee(String firstName, String secondName, String lastName, String phone, String inn, String workRecordBook, EmployeeType emplpyeeType){
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setSecondName(secondName);
            employee.setLastName(lastName);
            employee.setPhone(phone);
            employee.setInn(inn);
            employee.setWorkRecordBook(workRecordBook);
            employee.setEmplpyeeType(emplpyeeType);
            return employee;
        }
        public static Author createAuthor (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
            Author author = new Author();
            author.setFirstName(firstName);
            author.setSecondName(secondName);
            author.setLastName(lastName);
            author.setPhone(phone);
            author.setEmail(email);
            author.setDegree(degree);
            author.setOrganization(organization);
            return author;
    }

    public static PriceParameters createPriceParametersSet(double pagePrice, Set<Long> set, double workPrice, String validFromDate, String validToDate){
        PriceParameters priceParameters = new PriceParameters();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public static PriceParametersList createPriceParametersList(double pagePrice, List<Long> list, double workPrice, String validFromDate, String validToDate){
        PriceParametersList priceParameters = new PriceParametersList();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(list);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public static PriceParametersMap createPriceParametersMap(double pagePrice, Map<Long, CoverType> map, double workPrice, String validFromDate, String validToDate){
        PriceParametersMap priceParameters = new PriceParametersMap();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(map);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public static PriceParametersCollection createPriceParametersCollection(double pagePrice, Set<CoverPrice> set, double workPrice, String validFromDate, String validToDate){
        PriceParametersCollection priceParameters = new PriceParametersCollection();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public static CoverPrice createCoverPrice(CoverType coverType, double price){
        CoverPrice coverPrice = new CoverPrice();
        coverPrice.setCoverType(coverType);
        coverPrice.setPrice(price);
        return coverPrice;
    }

    public static Author5 createAuthor5 (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
        Author5 author = new Author5();
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        author.setLastName(lastName);
        author.setPhone(phone);
        author.setEmail(email);
        author.setDegree(degree);
        author.setOrganization(organization);
        return author;
    }

    public static Book5 createBook5 (Author5 author, String title, int numberOfPages){
        Book5 book = new Book5();
        book.setAuthor(author);
        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        return book;
    }
}
