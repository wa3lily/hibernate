package ru.sfedu.hibernate.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.hibernate.lab5.model.many_to_one.*;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Lab5ProviderTest extends TestBase6 {

    private static Logger log = LogManager.getLogger(Lab5ProviderTest.class);
    Lab5Provider instance = new Lab5Provider();

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

}