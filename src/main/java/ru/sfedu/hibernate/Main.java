package ru.sfedu.hibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.hibernate.enums.BookStatus;
import ru.sfedu.hibernate.enums.CorrectionsStatus;
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
import ru.sfedu.hibernate.lab5.model.*;
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
            Author7 author = createAuthor7("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
            Book7 book = createBook7(author,"Цифровая бухгалтерия",400);
            provider.save(author);
            provider.save(book);
            switch (s[1]) {
                case "criteria":
                    long start = System.currentTimeMillis();
                    System.out.println(provider.getByIdCriteria(Author7.class,Long.parseLong(s[2])).toString());
                    Thread.sleep(1000);
                    long finish = System.currentTimeMillis();
                    long elapsed = finish - start;
                    return " "+elapsed;
                case "hql":
                    long start1 = System.currentTimeMillis();
                    System.out.println(provider.getById(Author7.class,Long.parseLong(s[2])).toString());
                    Thread.sleep(1000);
                    long finish1 = System.currentTimeMillis();
                    long elapsed1 = finish1 - start1;
                    return " "+elapsed1;
                case "sql":
                    long start2 = System.currentTimeMillis();
                    System.out.println(provider.getByIdNativeSQL(Author7.class,Long.parseLong(s[2])).toString());
                    Thread.sleep(1000);
                    long finish2 = System.currentTimeMillis();
                    long elapsed2 = finish2 - start2;
                    return " "+elapsed2;
                case "many2one":
                    Author7 author7 = createAuthor7("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
                    Book7 book7 = createBook7(author7,"Цифровая бухгалтерия",400);
                    Long resultA = provider.save(author7);
                    Long resultB = provider.save(book7);
                    switch (s[2]){
                        case "save":
                            Author7 author8 = createAuthor7(s[3],s[4],s[5],s[6], s[7], s[8], s[9]);
                            Long res = provider.save(author8);
                            return provider.getById(Author7.class,res).toString();
                        case "delete":
                            switch (s[3]){
                                case "author":
                                    provider.delete(Author7.class,Long.parseLong(s[4]));
                                    return provider.getById(Author7.class,Long.parseLong(s[4])).toString();
                                case "book":
                                    provider.delete(Book7.class,Long.parseLong(s[4]));
                                    return provider.getById(Book7.class,Long.parseLong(s[4])).toString();
                            }
                        case "update":
                            switch (s[3]){
                                case "author":
                                    Author7 author7u = provider.getById(Author7.class,resultA).orElse(null);
                                    author7u.setFirstName(s[4]);
                                    provider.update(author7u);
                                    return provider.getById(Author7.class,resultA).toString();
                                case "book":
                                    Book7 book7u = provider.getById(Book7.class,resultB).orElse(null);
                                    book7u.setTitle(s[4]);
                                    provider.update(book7u);
                                    return provider.getById(Book7.class,resultB).toString();
                            }
                        case "get":
                            switch (s[3]){
                                case "author":
                                    return provider.getById(Author7.class,Long.parseLong(s[4])).toString();
                                case "book":
                                    return provider.getById(Book7.class,Long.parseLong(s[4])).toString();
                            }
                    }
                case "many2many":
                    Set<CoverPrice7> set = new HashSet<>();
                    CoverPrice7 coverPrice = createCoverPrice7(CoverType.RIGID_COVER, 123.5);
                    CoverPrice7 coverPrice2 = createCoverPrice7(CoverType.PAPERBACK, 143.8);
                    Long result1 = provider.save(coverPrice);
                    Long result2 = provider.save(coverPrice2);
                    set.add(coverPrice);
                    set.add(coverPrice2);
                    PriceParameters7 priceParameters = createPriceParameters7( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
                    Long resultAm2m = provider.save(priceParameters);
                    switch (s[2]){
                        case "delete":
                            switch (s[3]){
                                case "coverPrice":
                                    provider.delete(CoverPrice7.class, Long.parseLong(s[4]));
                                    return provider.getById(CoverPrice7.class, Long.parseLong(s[4])).toString();
                                case "priceParameters":
                                    provider.delete(PriceParameters7.class, Long.parseLong(s[4]));
                                    return provider.getById(PriceParameters7.class, Long.parseLong(s[4])).toString();
                            }
                        case "update":
                            switch (s[3]){
                                case "coverPrice":
                                    CoverPrice7 coverPriceU = provider.getById(CoverPrice7.class, result1).orElse(null);
                                    coverPriceU.setPrice(Double.parseDouble(s[4]));
                                    provider.update(coverPriceU);
                                    return provider.getById(CoverPrice7.class, result1).toString();
                                case "priceParameters":
                                    PriceParameters7 priceParametersU = provider.getById(PriceParameters7.class, resultAm2m).orElse(null);
                                    priceParametersU.setPagePrice(Double.parseDouble(s[4]));
                                    provider.update(priceParametersU);
                                    return provider.getById(PriceParameters7.class, resultAm2m).toString();
                            }
                        case "get":
                            switch (s[3]){
                                case "coverPrice":
                                    return provider.getById(CoverPrice7.class, Long.parseLong(s[4])).toString();
                                case "priceParameters":
                                    return provider.getById(PriceParameters7.class, Long.parseLong(s[4])).toString();
                            }
                    }
                case "one2one":
                    Meeting7 meetingo2o = createMeeting7("2020-12-15 11:55",true, false);
                    Author7 authoro2o = createAuthor7("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
                    Order7 ordero2o = createOrder7(author,"Цифровая бухгалтерия",4,"2020-09-03", CoverType.RIGID_COVER, null, null, null, 229, 100, 9700.75 , BookStatus.EDITING  );
                    Corrections7 corrections = corrections = createCorrections7(35, "Цифровой контроль - это компьютерные системы",
                            "Цифровой контроль представляет собой компьютерные системы", "Повторяется конструкция", ordero2o, meetingo2o, CorrectionsStatus.WAIT_AUTHOR_AGR );

                    Long resultM = provider.save(meetingo2o);
                    provider.save(authoro2o);
                    provider.save(ordero2o);
                    Long resultC = provider.save(corrections);
                    switch (s[2]){
                        case "save":
                            switch (s[3]){
                                case "meet":
                                    Meeting7 meetings = createMeeting7(s[4],Boolean.parseBoolean(s[4]),  Boolean.parseBoolean(s[4]));
                                    Long resMS = provider.save(meetings);
                                    return provider.getById(Meeting7.class, resMS).toString();
                                case "cor":
                                    Corrections7 correctionsS = createCorrections7(Integer.parseInt(s[4]), s[5], s[6], s[7], ordero2o, meetingo2o, CorrectionsStatus.WAIT_AUTHOR_AGR );
                                    Long resMC = provider.save(correctionsS);
                                    return provider.getById(Corrections7.class, resMC).toString();
                            }
                        case "delete":
                            switch (s[3]){
                                case "meet":
                                    provider.delete(Meeting7.class, Long.parseLong(s[4]));
                                    return provider.getById(Meeting7.class, Long.parseLong(s[4])).toString();
                                case "cor":
                                    provider.delete(Corrections7.class, Long.parseLong(s[4]));
                                    return provider.getById(Corrections7.class, Long.parseLong(s[4])).toString();
                            }
                        case "update":
                            switch (s[3]){
                                case "meet":
                                    Meeting7 meetingU = provider.getById(Meeting7.class, resultM).orElse(null);
                                    meetingU.setEditorAgreement(Boolean.parseBoolean(s[4]));
                                    provider.update(meetingU);
                                    return provider.getById(Meeting7.class, resultM).toString();
                                case "cor":
                                    Corrections7 correctionsU = provider.getById(Corrections7.class, resultC).orElse(null);
                                    correctionsU.setComment("AnotherComment");
                                    provider.update(correctionsU);
                                    return provider.getById(Corrections7.class, resultC).toString();
                            }
                        case "get":
                            switch (s[3]){
                                case "meet":
                                    return provider.getById(Meeting7.class, Long.parseLong(s[4])).toString();
                                case "cor":
                                    return provider.getById(Corrections7.class, Long.parseLong(s[4])).toString();
                            }
                    }
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

    public static Author7 createAuthor7 (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
        Author7 author = new Author7();
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        author.setLastName(lastName);
        author.setPhone(phone);
        author.setEmail(email);
        author.setDegree(degree);
        author.setOrganization(organization);
        return author;
    }

    public static Book7 createBook7 (Author7 author, String title, int numberOfPages){
        Book7 book = new Book7();
        book.setAuthor(author);
        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        return book;
    }

    public static PriceParameters7 createPriceParameters7(double pagePrice, Set<CoverPrice7> set, double workPrice, String validFromDate, String validToDate){
        PriceParameters7 priceParameters = new PriceParameters7();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public static CoverPrice7 createCoverPrice7(CoverType coverType, double price){
        CoverPrice7 coverPrice = new CoverPrice7();
        coverPrice.setCoverType(coverType);
        coverPrice.setPrice(price);
        return coverPrice;
    }

    public static Corrections7 createCorrections7 (int page, String textBefore, String textAfter, String comment, Order7 order, Meeting7 meet, CorrectionsStatus status){
        Corrections7 correction = new Corrections7();
        correction.setPage(page);
        correction.setTextBefore(textBefore);
        correction.setTextAfter(textAfter);
        correction.setComment(comment);
        correction.setOrder(order);
        correction.setMeet(meet);
        correction.setStatus(status);
        return correction;
    }

    public static Meeting7 createMeeting7 (String meetDate, boolean authorAgreement, boolean editorAgreement){
        Meeting7 meeting = new Meeting7();
        meeting.setMeetDate(meetDate);
        meeting.setAuthorAgreement(authorAgreement);
        meeting.setEditorAgreement(editorAgreement);
        return meeting;
    }

    public static Order7 createOrder7 (Author7 author, String title, int numberOfPages, String orderDate,
                               CoverType coverType, Employee7 bookMaker, Employee7 bookEditor, PriceParameters7 bookPriceParameters,
                               int finalNumberOfPages, int numberOfCopies, double price, BookStatus bookStatus){
        Order7 order = new Order7();
        order.setAuthor(author);
        order.setTitle(title);
        order.setNumberOfPages(numberOfPages);
        order.setOrderDate(orderDate);
        order.setCoverType(coverType);
        order.setBookMaker(bookMaker);
        order.setBookEditor(bookEditor);
        order.setBookPriceParameters(bookPriceParameters);
        order.setFinalNumberOfPages(finalNumberOfPages);
        order.setNumberOfCopies(numberOfCopies);
        order.setPrice(price);
        order.setBookStatus(bookStatus);
        return order;
    }

    public static People7 createPeople7 (String firstName, String secondName, String lastName, String phone){
        People7 people = new People7();
        people.setFirstName(firstName);
        people.setSecondName(secondName);
        people.setLastName(lastName);
        people.setPhone(phone);
        return people;
    }

    public static Employee7 createEmployee7 (String firstName, String secondName, String lastName, String phone, String inn, String workRecordBook, EmployeeType emplpyeeType){
        Employee7 employee = new Employee7();
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setLastName(lastName);
        employee.setPhone(phone);
        employee.setInn(inn);
        employee.setWorkRecordBook(workRecordBook);
        employee.setEmplpyeeType(emplpyeeType);
        return employee;
    }
}
