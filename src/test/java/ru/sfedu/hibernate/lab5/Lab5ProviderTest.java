package ru.sfedu.hibernate.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.lab5.model.many_to_many.CoverPrice5;
import ru.sfedu.hibernate.lab5.model.many_to_many.PriceParameters5;
import ru.sfedu.hibernate.lab5.model.many_to_one.*;
import ru.sfedu.hibernate.lab5.model.one_to_one.Author6;
import ru.sfedu.hibernate.lab5.model.one_to_one.Book6;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


class Lab5ProviderTest extends TestBase6 {

    private static Logger log = LogManager.getLogger(Lab5ProviderTest.class);
    Lab5Provider instance = new Lab5Provider();

    @Test
    public void TestTime() throws InterruptedException {
        Author5 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Long resultA = instance.save(author);
        long start = System.currentTimeMillis();
        log.info(instance.getByIdCriteria(Author5.class,resultA).get());
        Thread.sleep(1000);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        log.info("Criteria: " + elapsed +" мс");
        start = System.currentTimeMillis();
        log.info(instance.getById(Author5.class,resultA).get());
        Thread.sleep(1000);
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        log.info("HQL: " + elapsed +" мс");
        start = System.currentTimeMillis();
        log.info(instance.getByIdNativeSQL(Author5.class,resultA).get());
        Thread.sleep(1000);
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        log.info("SQL: " + elapsed +" мс");
    }

    //many to one
    @Test
    public void TestSaveSuccess() {
        log.info("saveSuccess");
        Author5 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book5 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author5 author1 = instance.getById(Author5.class, resultA).get();
        Book5 book1 = instance.getById(Book5.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
    }

    @Test
    public void TestGetByIdFail() {
        log.info("GetByIdFail");
        Optional<Author5> entity1 = instance.getById(Author5.class, 200L);
        assertNull(entity1.orElse(null));
        Optional<Book5> entity2 = instance.getById(Book5.class, 200L);
        assertNull(entity2.orElse(null));
    }

    @Test
    public void TestDeleteSuccess() {
        log.info("DeleteSuccess");
        Author5 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book5 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author5 author1 = instance.getById(Author5.class, resultA).get();
        Book5 book1 = instance.getById(Book5.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        log.debug(instance.delete(Book5.class,resultB));
        log.debug(instance.delete(Author5.class,resultA));
        Author5 author2 = instance.getById(Author5.class, resultA).orElse(null);
        Book5 book2 = instance.getById(Book5.class, resultB).orElse(null);
        assertNull(author2);
        assertNull(book2);
    }

    @Test
    public void TestDeleteFail() {
        log.info("DeleteFail");
        Author5 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book5 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author5 author1 = instance.getById(Author5.class, resultA).get();
        Book5 book1 = instance.getById(Book5.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        log.debug(instance.delete(Author5.class,resultA));
        Author5 author2 = instance.getById(Author5.class, resultA).orElse(null);
        Book5 book2 = instance.getById(Book5.class, resultB).orElse(null);
        assertNull(author2);
        assertNull(book2);
    }

    @Test
    public void TestUpdateSuccess(){
        log.info("UpdateSuccess");
        Author5 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book5 book = createBook(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author5 author1 = instance.getById(Author5.class, resultA).get();
        Book5 book1 = instance.getById(Book5.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        author1.setFirstName("Иван");
        book1.setNumberOfPages(1000);
        instance.update(author1);
        instance.update(book1);
        Author5 author2 = instance.getById(Author5.class, resultA).orElse(null);
        Book5 book2 = instance.getById(Book5.class, resultB).orElse(null);
        assertEquals(author1, author2);
        assertEquals(book1, book2);
    }

    @Test
    public void TestUpdateFail(){
        log.info("UpdateFail");
        Long id1 = 203L;
        Long id2 = 204L;
        Author5 author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book5 book = createBook(author,"Цифровая бухгалтерия",400);
        author.setId(id1);
        book.setId(id2);
        instance.update(author);
        instance.update(book);
        Author5 author1 = instance.getById(Author5.class, id1).orElse(null);
        Book5 employee1 = instance.getById(Book5.class, id2).orElse(null);
        assertNull(author1);
        assertNull(employee1);
    }

    //many to many
    @Test
    public void TestSaveSuccessManyToMany() {
        log.info("saveSuccessManyToMany");
        Set<CoverPrice5> set = new HashSet<>();
        CoverPrice5 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice5 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters5 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters5 priceParameters1 = instance.getById(PriceParameters5.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
    }

    @Test
    public void TestGetByIdFailManyToMany() {
        log.info("GetByIdFailManyToMany");
        Optional<CoverPrice5> entity1 = instance.getById(CoverPrice5.class, 200L);
        assertNull(entity1.orElse(null));
        Optional<PriceParameters5> entity2 = instance.getById(PriceParameters5.class, 200L);
        assertNull(entity2.orElse(null));
    }

    @Test
    public void TestDeleteSuccessManyToMany() {
        log.info("DeleteSuccessManyToMany");
        Set<CoverPrice5> set = new HashSet<>();
        CoverPrice5 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice5 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters5 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters5 priceParameters1 = instance.getById(PriceParameters5.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        log.debug(instance.delete(CoverPrice5.class,result1));
        log.debug(instance.delete(CoverPrice5.class,result2));
        log.debug(instance.delete(PriceParameters5.class,resultA));
        CoverPrice5 coverPrice3 = instance.getById(CoverPrice5.class, result1).orElse(null);
        CoverPrice5 coverPrice4 = instance.getById(CoverPrice5.class, result2).orElse(null);
        PriceParameters5 priceParameters2 = instance.getById(PriceParameters5.class, resultA).orElse(null);
        assertNull(coverPrice3);
        assertNull(coverPrice4);
        assertNull(priceParameters2);
    }

    @Test
    public void TestDeleteFailManyToMany() {
        log.info("DeleteFailManyToMany");
        Set<CoverPrice5> set = new HashSet<>();
        CoverPrice5 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice5 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters5 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters5 priceParameters1 = instance.getById(PriceParameters5.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        log.debug(instance.delete(CoverPrice5.class,result1));
        log.debug(instance.delete(CoverPrice5.class,result2));
        CoverPrice5 coverPrice3 = instance.getById(CoverPrice5.class, result1).orElse(null);
        CoverPrice5 coverPrice4 = instance.getById(CoverPrice5.class, result2).orElse(null);
        PriceParameters5 priceParameters2 = instance.getById(PriceParameters5.class, resultA).orElse(null);
        assertNull(coverPrice3);
        assertNull(coverPrice4);
        assertNull(priceParameters2);
    }

    @Test
    public void TestUpdateSuccessManyToMany(){
        log.info("UpdateSuccessManyToMany");
        Set<CoverPrice5> set = new HashSet<>();
        CoverPrice5 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice5 coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        Long result1 = instance.save(coverPrice);
        Long result2 = instance.save(coverPrice2);
        set.add(coverPrice);
        set.add(coverPrice2);
        PriceParameters5 priceParameters = createPriceParameters( 13.4, set, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters5 priceParameters1 = instance.getById(PriceParameters5.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        coverPrice.setPrice(100);
        log.debug(instance.update(coverPrice));
        CoverPrice5 coverPrice3 = instance.getById(CoverPrice5.class, result1).orElse(null);
        assertEquals(coverPrice, coverPrice3);
    }

    @Test
    public void TestUpdateFailManyToMany(){
        log.info("UpdateFailManyToMany");
        Long id1 = 203L;
        CoverPrice5 coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        coverPrice.setId(id1);
        instance.update(coverPrice);
        CoverPrice5 coverPrice1 = instance.getById(CoverPrice5.class, id1).orElse(null);
        assertNull(coverPrice1);
    }

    //one2one
    @Test
    public void TestSaveSuccessOne2One() {
        log.info("saveSuccessOne2One");
        Author6 author = createAuthor6("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book6 book = createBook6(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author6 author1 = instance.getById(Author6.class, resultA).get();
        Book6 book1 = instance.getById(Book6.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
    }

    @Test
    public void TestGetByIdFailOne2One() {
        log.info("GetByIdFailOne2One");
        Optional<Author6> entity1 = instance.getById(Author6.class, 200L);
        assertNull(entity1.orElse(null));
        Optional<Book6> entity2 = instance.getById(Book6.class, 200L);
        assertNull(entity2.orElse(null));
    }

    @Test
    public void TestDeleteSuccessOne2One() {
        log.info("DeleteSuccessOne2One");
        Author6 author = createAuthor6("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book6 book = createBook6(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author6 author1 = instance.getById(Author6.class, resultA).get();
        Book6 book1 = instance.getById(Book6.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        log.debug(instance.delete(Book6.class,resultB));
        log.debug(instance.delete(Author6.class,resultA));
        Author6 author2 = instance.getById(Author6.class, resultA).orElse(null);
        Book6 book2 = instance.getById(Book6.class, resultB).orElse(null);
        assertNull(author2);
        assertNull(book2);
    }

    @Test
    public void TestDeleteFailOne2One() {
        log.info("DeleteFailOne2One");
        log.debug(instance.delete(Book6.class,404L));
        log.debug(instance.delete(Author6.class,404L));
        Author6 author2 = instance.getById(Author6.class, 404L).orElse(null);
        Book6 book2 = instance.getById(Book6.class, 404L).orElse(null);
        assertNull(author2);
        assertNull(book2);
    }

    @Test
    public void TestUpdateSuccessOne2One(){
        log.info("UpdateSuccessOne2One");
        Author6 author = createAuthor6("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book6 book = createBook6(author,"Цифровая бухгалтерия",400);
        Long resultA = instance.save(author);
        Long resultB = instance.save(book);
        Author6 author1 = instance.getById(Author6.class, resultA).get();
        Book6 book1 = instance.getById(Book6.class, resultB).get();
        log.debug(author1);
        log.debug(book1);
        assertEquals(author, author1);
        assertEquals(book, book1);
        author1.setFirstName("Иван");
        book1.setNumberOfPages(1000);
        instance.update(author1);
        instance.update(book1);
        Author6 author2 = instance.getById(Author6.class, resultA).orElse(null);
        Book6 book2 = instance.getById(Book6.class, resultB).orElse(null);
        assertEquals(author1, author2);
        assertEquals(book1, book2);
    }

    @Test
    public void TestUpdateFailOne2One(){
        log.info("UpdateFailOne2One");
        Long id1 = 203L;
        Long id2 = 204L;
        Author6 author = createAuthor6("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Book6 book = createBook6(author,"Цифровая бухгалтерия",400);
        author.setId(id1);
        book.setId(id2);
        instance.update(author);
        instance.update(book);
        Author6 author1 = instance.getById(Author6.class, id1).orElse(null);
        Book6 employee1 = instance.getById(Book6.class, id2).orElse(null);
        assertNull(author1);
        assertNull(employee1);
    }



}