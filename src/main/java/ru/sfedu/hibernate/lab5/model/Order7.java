package ru.sfedu.hibernate.lab5.model;

import ru.sfedu.hibernate.enums.BookStatus;
import ru.sfedu.hibernate.enums.CoverType;

import javax.persistence.*;
import java.util.Objects;

@Entity()
@Table()
@DiscriminatorValue( "Order" )
public class Order7 extends Book7{

    private String orderDate;
    private CoverType coverType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maker_id", nullable = true)
    private Employee7 bookMaker;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editor_id", nullable = true)
    private Employee7 bookEditor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_parameters_id", nullable = true)
    private PriceParameters7 bookPriceParameters;
    private int finalNumberOfPages;
    private int numberOfCopies;
    private double price = -1;
    private BookStatus bookStatus;

    public Order7 () { };

    public void setOrderDate (String newVar) {
        orderDate = newVar;
    }

    public String getOrderDate () {
        return orderDate;
    }

    public void setCoverType (CoverType newVar) {
        coverType = newVar;
    }

    public CoverType getCoverType () {
        return coverType;
    }

    public void setBookMaker (Employee7 newVar) {
        bookMaker = newVar;
    }

    public Employee7 getBookMaker () {
        return bookMaker;
    }

    public void setBookEditor (Employee7 newVar) {
        bookEditor = newVar;
    }

    public Employee7 getBookEditor () {
        return bookEditor;
    }

    public void setBookPriceParameters (PriceParameters7 newVar) {
        bookPriceParameters = newVar;
    }

    public PriceParameters7 getBookPriceParameters () {
        return bookPriceParameters;
    }

    public void setFinalNumberOfPages (int newVar) {
        finalNumberOfPages = newVar;
    }

    public int getFinalNumberOfPages () {
        return finalNumberOfPages;
    }

    public void setNumberOfCopies (int newVar) {
        numberOfCopies = newVar;
    }

    public int getNumberOfCopies () {
        return numberOfCopies;
    }

    public void setPrice (double newVar) {
        price = newVar;
    }

    public double getPrice () {
        return price;
    }

    public void setBookStatus (BookStatus newVar) {
        bookStatus = newVar;
    }

    public BookStatus getBookStatus () {
        return bookStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order7 order = (Order7) o;
        return finalNumberOfPages == order.finalNumberOfPages &&
                numberOfCopies == order.numberOfCopies &&
                Double.compare(order.price, price) == 0 &&
                Objects.equals(orderDate, order.orderDate) &&
                coverType == order.coverType &&
                (bookMaker == null && order.bookMaker == null || bookMaker.getId() == order.bookMaker.getId()) &&
                (bookEditor == null && order.bookEditor == null || bookEditor.getId() == order.bookEditor.getId()) &&
                (bookPriceParameters == null && order.bookPriceParameters == null || bookPriceParameters.getId() == order.bookPriceParameters.getId()) &&
                bookStatus == order.bookStatus;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), orderDate, coverType, bookMaker, bookEditor, bookPriceParameters, finalNumberOfPages, numberOfCopies, price, bookStatus);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + super.getId() +
                ", author=" + super.getAuthor() +
                ", title='" + super.getTitle() + '\'' +
                ", numberOfPages=" + super.getNumberOfPages() +
                ", orderDate='" + orderDate + '\'' +
                ", coverType=" + coverType +
                ", bookMaker=" + bookMaker.getId() +
                ", bookEditor=" + bookEditor.getId() +
                ", bookPriceParameters=" + bookPriceParameters.getId() +
                ", finalNumberOfPages=" + finalNumberOfPages +
                ", numberOfCopies=" + numberOfCopies +
                ", price=" + price +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
