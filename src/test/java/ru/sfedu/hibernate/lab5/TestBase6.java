package ru.sfedu.hibernate.lab5;

import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.lab4.model.collection.CoverPrice;
import ru.sfedu.hibernate.lab4.model.collection.PriceParametersCollection;
import ru.sfedu.hibernate.lab5.model.many_to_many.CoverPrice5;
import ru.sfedu.hibernate.lab5.model.many_to_many.PriceParameters5;
import ru.sfedu.hibernate.lab5.model.many_to_one.Author5;
import ru.sfedu.hibernate.lab5.model.many_to_one.Book5;
import ru.sfedu.hibernate.lab5.model.one_to_one.Author6;
import ru.sfedu.hibernate.lab5.model.one_to_one.Book6;

import java.util.Set;

public class TestBase6 {

    public Author5 createAuthor (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
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

    public Book5 createBook (Author5 author, String title, int numberOfPages){
        Book5 book = new Book5();
        book.setAuthor(author);
        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        return book;
    }

    public PriceParameters5 createPriceParameters(double pagePrice, Set<CoverPrice5> set, double workPrice, String validFromDate, String validToDate){
        PriceParameters5 priceParameters = new PriceParameters5();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public CoverPrice5 createCoverPrice(CoverType coverType, double price){
        CoverPrice5 coverPrice = new CoverPrice5();
        coverPrice.setCoverType(coverType);
        coverPrice.setPrice(price);
        return coverPrice;
    }

    public Author6 createAuthor6(String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
        Author6 author = new Author6();
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        author.setLastName(lastName);
        author.setPhone(phone);
        author.setEmail(email);
        author.setDegree(degree);
        author.setOrganization(organization);
        return author;
    }

    public Book6 createBook6 (Author6 author, String title, int numberOfPages){
        Book6 book = new Book6();
        book.setAuthor(author);
        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        return book;
    }
}
