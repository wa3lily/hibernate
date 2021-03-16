package ru.sfedu.hibernate.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.hibernate.enums.BookStatus;
import ru.sfedu.hibernate.enums.CorrectionsStatus;
import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.lab5.model.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Lab5Provider2Test extends TestBase7{

    private static Logger log = LogManager.getLogger(Lab5ProviderTest.class);
    Lab5Provider instance = new Lab5Provider();

    @Test
    public void TestTime() throws InterruptedException {
        People7 people = createPeople("Виктор","Иванович","Ткач","83456789012");
        Long resultA = instance.save(people);
        long start = System.currentTimeMillis();
        log.info(instance.getByIdCriteria(People7.class,resultA).get());
        Thread.sleep(1000);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        log.info("Criteria: " + elapsed +" мс");
        start = System.currentTimeMillis();
        log.info(instance.getById(People7.class,resultA).get());
        Thread.sleep(1000);
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        log.info("HQL: " + elapsed +" мс");
        start = System.currentTimeMillis();
        log.info(instance.getByIdNativeSQL(People7.class,resultA).get());
        Thread.sleep(1000);
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        log.info("SQL: " + elapsed +" мс");
    }

    //many to one
    @Test
    public void TestSaveSuccess() {
        log.info("saveSuccess");
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book7 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author7 author1 = instance.getById(Author7.class, resultA).get();
        Book7 book1 = instance.getById(Book7.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
    }

    @Test
    public void TestGetByIdFail() {
        log.info("GetByIdFail");
        Optional<Author7> entity1 = instance.getById(Author7.class, 200L);
        assertNull(entity1.orElse(null));
        Optional<Book7> entity2 = instance.getById(Book7.class, 200L);
        assertNull(entity2.orElse(null));
    }

    @Test
    public void TestDeleteSuccess() {
        log.info("DeleteSuccess");
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book7 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author7 author1 = instance.getById(Author7.class, resultA).get();
        Book7 book1 = instance.getById(Book7.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        log.debug(instance.delete(Book7.class,resultB));
        log.debug(instance.delete(Author7.class,resultA));
        Author7 author2 = instance.getById(Author7.class, resultA).orElse(null);
        Book7 book2 = instance.getById(Book7.class, resultB).orElse(null);
        assertNull(author2);
        assertNull(book2);
    }

    @Test
    public void TestDeleteFail() {
        log.info("DeleteFail");
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book7 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author7 author1 = instance.getById(Author7.class, resultA).get();
        Book7 book1 = instance.getById(Book7.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        log.debug(instance.delete(Author7.class,resultA));
        Author7 author2 = instance.getById(Author7.class, resultA).orElse(null);
        Book7 book2 = instance.getById(Book7.class, resultB).orElse(null);
        assertNull(author2);
        assertNull(book2);
    }

    @Test
    public void TestUpdateSuccess(){
        log.info("UpdateSuccess");
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book7 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author7 author1 = instance.getById(Author7.class, resultA).get();
        Book7 book1 = instance.getById(Book7.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        author1.setFirstName("Иван");
        book1.setNumberOfPages(1000);
        instance.update(author1);
        instance.update(book1);
        Author7 author2 = instance.getById(Author7.class, resultA).orElse(null);
        Book7 book2 = instance.getById(Book7.class, resultB).orElse(null);
        assertEquals(author1, author2);
        assertEquals(book1, book2);
    }

    @Test
    public void TestUpdateFail(){
        log.info("UpdateFail");
        Long id1 = 203L;
        Long id2 = 204L;
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book7 book = createBook(author,"Цифровая бухгалтерия",400);
        author.setId(id1);
        book.setId(id2);
        instance.update(author);
        instance.update(book);
        Author7 author1 = instance.getById(Author7.class, id1).orElse(null);
        Book7 employee1 = instance.getById(Book7.class, id2).orElse(null);
        assertNull(author1);
        assertNull(employee1);
    }

    //many to many
    @Test
    public void TestSaveSuccessManyToMany() {
        log.info("saveSuccessManyToMany");
        Set<CoverPrice7> set = new HashSet<>();
        CoverPrice7 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice7 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters7 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters7 priceParameters1 = instance.getById(PriceParameters7.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
    }

    @Test
    public void TestGetByIdFailManyToMany() {
        log.info("GetByIdFailManyToMany");
        Optional<CoverPrice7> entity1 = instance.getById(CoverPrice7.class, 200L);
        assertNull(entity1.orElse(null));
        Optional<PriceParameters7> entity2 = instance.getById(PriceParameters7.class, 200L);
        assertNull(entity2.orElse(null));
    }

    @Test
    public void TestDeleteSuccessManyToMany() {
        log.info("DeleteSuccessManyToMany");
        Set<CoverPrice7> set = new HashSet<>();
        CoverPrice7 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice7 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters7 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters7 priceParameters1 = instance.getById(PriceParameters7.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        log.debug(instance.delete(PriceParameters7.class,resultA));
        log.debug(instance.delete(CoverPrice7.class,result1));
        log.debug(instance.delete(CoverPrice7.class,result2));
        CoverPrice7 coverPrice3 = instance.getById(CoverPrice7.class, result1).orElse(null);
        CoverPrice7 coverPrice4 = instance.getById(CoverPrice7.class, result2).orElse(null);
        PriceParameters7 priceParameters2 = instance.getById(PriceParameters7.class, resultA).orElse(null);
        assertNull(coverPrice3);
        assertNull(coverPrice4);
        assertNull(priceParameters2);
    }

    @Test
    public void TestDeleteFailManyToMany() {
        log.info("DeleteFailManyToMany");
        Set<CoverPrice7> set = new HashSet<>();
        CoverPrice7 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice7 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters7 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters7 priceParameters1 = instance.getById(PriceParameters7.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        log.debug(instance.delete(PriceParameters7.class,resultA));
//        log.debug(instance.delete(CoverPrice7.class,result1));
//        log.debug(instance.delete(CoverPrice7.class,result2));
        CoverPrice7 coverPrice3 = instance.getById(CoverPrice7.class, result1).orElse(null);
        CoverPrice7 coverPrice4 = instance.getById(CoverPrice7.class, result2).orElse(null);
        PriceParameters7 priceParameters2 = instance.getById(PriceParameters7.class, resultA).orElse(null);
        assertEquals(coverPrice, coverPrice3);
        assertEquals(coverPrice2, coverPrice4);
        assertNull(priceParameters2);
    }

    @Test
    public void TestUpdateSuccessManyToMany(){
        log.info("UpdateSuccessManyToMany");
        Set<CoverPrice7> set = new HashSet<>();
        CoverPrice7 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice7 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters7 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters7 priceParameters1 = instance.getById(PriceParameters7.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        coverPrice.setPrice(100);
        log.debug(instance.update(coverPrice));
        CoverPrice7 coverPrice3 = instance.getById(CoverPrice7.class, result1).orElse(null);
        assertEquals(coverPrice, coverPrice3);
    }

    @Test
    public void TestUpdateFailManyToMany(){
        log.info("UpdateFailManyToMany");
        Long id1 = 203L;
        CoverPrice7 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        coverPrice.setId(id1);
        instance.update(coverPrice);
        CoverPrice7 coverPrice1 = instance.getById(CoverPrice7.class, id1).orElse(null);
        assertNull(coverPrice1);
    }

    //one2one
    @Test
    public void TestSaveSuccessOne2One() {
        log.info("saveSuccessOne2One");
        Meeting7 meeting = createMeeting("2020-12-15 11:55",true, false);
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Order7 order = createOrder(author,"Цифровая бухгалтерия",4,"2020-09-03", CoverType.RIGID_COVER, null, null, null, 229, 100, 9700.75 , BookStatus.EDITING  );
        Corrections7 corrections = corrections = createCorrections(35, "Цифровой контроль - это компьютерные системы",
                "Цифровой контроль представляет собой компьютерные системы", "Повторяется конструкция", order, meeting, CorrectionsStatus.WAIT_AUTHOR_AGR );

        Long resultM = instance.save(meeting);
        Long resultA = instance.save(author);
        Long resultO = instance.save(order);
        Long resultC = instance.save(corrections);

        Meeting7 meeting1 = instance.getById(Meeting7.class, resultM).get();
        Corrections7 corrections1 = instance.getById(Corrections7.class, resultC).get();
        log.debug(meeting1);
        log.debug(corrections1);
        assertEquals(meeting, meeting1);
        assertEquals(corrections, corrections1);
    }

    @Test
    public void TestGetByIdFailOne2One() {
        log.info("GetByIdFailOne2One");
        Optional<Meeting7> entity1 = instance.getById(Meeting7.class, 200L);
        assertNull(entity1.orElse(null));
        Optional<Corrections7> entity2 = instance.getById(Corrections7.class, 200L);
        assertNull(entity2.orElse(null));
    }

    @Test
    public void TestDeleteSuccessOne2One() {
        log.info("DeleteSuccessOne2One");
        Meeting7 meeting = createMeeting("2020-12-15 11:55",true, false);
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Order7 order = createOrder(author,"Цифровая бухгалтерия",4,"2020-09-03", CoverType.RIGID_COVER, null, null, null, 229, 100, 9700.75 , BookStatus.EDITING  );
        Corrections7 corrections = corrections = createCorrections(35, "Цифровой контроль - это компьютерные системы",
                "Цифровой контроль представляет собой компьютерные системы", "Повторяется конструкция", order, meeting, CorrectionsStatus.WAIT_AUTHOR_AGR );

        Long resultM = instance.save(meeting);
        Long resultA = instance.save(author);
        Long resultO = instance.save(order);
        Long resultC = instance.save(corrections);

        Meeting7 meeting1 = instance.getById(Meeting7.class, resultM).get();
        Corrections7 corrections1 = instance.getById(Corrections7.class, resultC).get();
        log.debug(meeting1);
        log.debug(corrections1);
        assertEquals(meeting, meeting1);
        assertEquals(corrections, corrections1);

        log.debug(instance.delete(Corrections7.class,resultC));
        Meeting7 meeting2 = instance.getById(Meeting7.class, resultM).orElse(null);
        Corrections7 corrections2 = instance.getById(Corrections7.class, resultC).orElse(null);
        assertNull(meeting2);
        assertNull(corrections2);
    }

    @Test
    public void TestDeleteFailOne2One() {
        log.info("DeleteFailOne2One");
        Meeting7 meeting = createMeeting("2020-12-15 11:55",true, false);
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Order7 order = createOrder(author,"Цифровая бухгалтерия",4,"2020-09-03", CoverType.RIGID_COVER, null, null, null, 229, 100, 9700.75 , BookStatus.EDITING  );
        Corrections7 corrections = corrections = createCorrections(35, "Цифровой контроль - это компьютерные системы",
                "Цифровой контроль представляет собой компьютерные системы", "Повторяется конструкция", order, meeting, CorrectionsStatus.WAIT_AUTHOR_AGR );

        Long resultM = instance.save(meeting);
        Long resultA = instance.save(author);
        Long resultO = instance.save(order);
        Long resultC = instance.save(corrections);

        Meeting7 meeting1 = instance.getById(Meeting7.class, resultM).get();
        Corrections7 corrections1 = instance.getById(Corrections7.class, resultC).get();
        log.debug(meeting1);
        log.debug(corrections1);
        assertEquals(meeting, meeting1);
        assertEquals(corrections, corrections1);

        log.debug(instance.delete(Meeting7.class,resultM));
        Meeting7 meeting2 = instance.getById(Meeting7.class, resultM).orElse(null);
        Corrections7 corrections2 = instance.getById(Corrections7.class, resultC).orElse(null);
        assertEquals(meeting1, meeting2);
        assertEquals(corrections1, corrections2);
    }

    @Test
    public void TestUpdateSuccessOne2One(){
        log.info("UpdateSuccessOne2One");
        Meeting7 meeting = createMeeting("2020-12-15 11:55",true, false);
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Order7 order = createOrder(author,"Цифровая бухгалтерия",4,"2020-09-03", CoverType.RIGID_COVER, null, null, null, 229, 100, 9700.75 , BookStatus.EDITING  );
        Corrections7 corrections = corrections = createCorrections(35, "Цифровой контроль - это компьютерные системы",
                "Цифровой контроль представляет собой компьютерные системы", "Повторяется конструкция", order, meeting, CorrectionsStatus.WAIT_AUTHOR_AGR );

        Long resultM = instance.save(meeting);
        Long resultA = instance.save(author);
        Long resultO = instance.save(order);
        Long resultC = instance.save(corrections);

        Meeting7 meeting1 = instance.getById(Meeting7.class, resultM).get();
        Corrections7 corrections1 = instance.getById(Corrections7.class, resultC).get();
        log.debug(meeting1);
        log.debug(corrections1);
        assertEquals(meeting, meeting1);
        assertEquals(corrections, corrections1);

        meeting1.setEditorAgreement(true);
        corrections1.setPage(1000);
        instance.update(meeting1);
        instance.update(corrections1);
        Meeting7 meeting2 = instance.getById(Meeting7.class, resultM).get();
        Corrections7 corrections2 = instance.getById(Corrections7.class, resultC).get();
        assertEquals(meeting1, meeting2);
        assertEquals(corrections1, corrections2);
    }

    @Test
    public void TestUpdateFailOne2One(){
        log.info("UpdateFailOne2One");
        Long id1 = 203L;
        Long id2 = 204L;
        Meeting7 meeting = createMeeting("2020-12-15 11:55",true, false);
        Author7 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Order7 order = createOrder(author,"Цифровая бухгалтерия",4,"2020-09-03", CoverType.RIGID_COVER, null, null, null, 229, 100, 9700.75 , BookStatus.EDITING  );
        Corrections7 corrections = corrections = createCorrections(35, "Цифровой контроль - это компьютерные системы",
                "Цифровой контроль представляет собой компьютерные системы", "Повторяется конструкция", order, meeting, CorrectionsStatus.WAIT_AUTHOR_AGR );
        meeting.setId(id1);
        corrections.setId(id2);
        instance.update(meeting);
        instance.update(corrections);
        Meeting7 meeting2 = instance.getById(Meeting7.class, id1).orElse(null);
        Corrections7 corrections2 = instance.getById(Corrections7.class, id2).orElse(null);
        assertNull(meeting2);
        assertNull(corrections2);
    }
}