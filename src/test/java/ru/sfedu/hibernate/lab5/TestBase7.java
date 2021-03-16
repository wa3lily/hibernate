package ru.sfedu.hibernate.lab5;

import ru.sfedu.hibernate.enums.BookStatus;
import ru.sfedu.hibernate.enums.CorrectionsStatus;
import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.enums.EmployeeType;
import ru.sfedu.hibernate.lab5.model.*;

import java.util.Set;

public class TestBase7 {

    public Author7 createAuthor (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
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

    public Book7 createBook (Author7 author, String title, int numberOfPages){
        Book7 book = new Book7();
        book.setAuthor(author);
        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        return book;
    }

    public PriceParameters7 createPriceParameters(double pagePrice, Set<CoverPrice7> set, double workPrice, String validFromDate, String validToDate){
        PriceParameters7 priceParameters = new PriceParameters7();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public CoverPrice7 createCoverPrice(CoverType coverType, double price){
        CoverPrice7 coverPrice = new CoverPrice7();
        coverPrice.setCoverType(coverType);
        coverPrice.setPrice(price);
        return coverPrice;
    }

    public Corrections7 createCorrections (int page, String textBefore, String textAfter, String comment, Order7 order, Meeting7 meet, CorrectionsStatus status){
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

    public Meeting7 createMeeting (String meetDate, boolean authorAgreement, boolean editorAgreement){
        Meeting7 meeting = new Meeting7();
        meeting.setMeetDate(meetDate);
        meeting.setAuthorAgreement(authorAgreement);
        meeting.setEditorAgreement(editorAgreement);
        return meeting;
    }

    public Order7 createOrder (Author7 author, String title, int numberOfPages, String orderDate,
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

    public People7 createPeople (String firstName, String secondName, String lastName, String phone){
        People7 people = new People7();
        people.setFirstName(firstName);
        people.setSecondName(secondName);
        people.setLastName(lastName);
        people.setPhone(phone);
        return people;
    }

    public Employee7 createEmployee (String firstName, String secondName, String lastName, String phone, String inn, String workRecordBook, EmployeeType emplpyeeType){
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
