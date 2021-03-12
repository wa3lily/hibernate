package ru.sfedu.hibernate.lab4.model.collection;

import org.hibernate.annotations.Parent;
import ru.sfedu.hibernate.enums.CoverType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CoverPrice implements Serializable {

    @Column(nullable = false)
    private CoverType coverType;
    @Column(nullable = false)
    private double price;

    public CoverPrice() {
    }


    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
        this.coverType = coverType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverPrice that = (CoverPrice) o;
        return Double.compare(that.price, price) == 0 && coverType == that.coverType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coverType, price);
    }

    @Override
    public String toString() {
        return "CoverPrice{" +
                "coverType=" + coverType +
                ", price=" + price +
                '}';
    }
}
