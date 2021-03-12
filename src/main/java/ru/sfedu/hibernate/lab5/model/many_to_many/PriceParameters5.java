package ru.sfedu.hibernate.lab5.model.many_to_many;

import ru.sfedu.hibernate.lab4.model.collection.CoverPrice;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema="lab5_many2many")
public class PriceParameters5 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double pagePrice;
    private double workPrice;
    private String validFromDate;
    private String validToDate;

    public PriceParameters5() { };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPagePrice (double newVar) {
        pagePrice = newVar;
    }

    public double getPagePrice () {
        return pagePrice;
    }

    @ManyToMany(mappedBy = "priceparameters")
    protected Set<CoverPrice5> coverPrice =  new HashSet<>();

    public Set<CoverPrice5> getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(Set<CoverPrice5> coverPrice) {
        this.coverPrice = coverPrice;
    }

    public void setWorkPrice (double newVar) {
        workPrice = newVar;
    }

    public double getWorkPrice () {
        return workPrice;
    }

    public void setValidFromDate (String newVar) {
        validFromDate = newVar;
    }

    public String getValidFromDate () {
        return validFromDate;
    }

    public void setValidToDate (String newVar) {
        validToDate = newVar;
    }


    public String getValidToDate () {
        return validToDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceParameters5 that = (PriceParameters5) o;
        return Double.compare(that.pagePrice, pagePrice) == 0 && Double.compare(that.workPrice, workPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(validFromDate, that.validFromDate) && Objects.equals(validToDate, that.validToDate) && Objects.equals(coverPrice, that.coverPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pagePrice, workPrice, validFromDate, validToDate, coverPrice);
    }

    @Override
    public String toString() {
        return "PriceParameters5{" +
                "id=" + id +
                ", pagePrice=" + pagePrice +
                ", workPrice=" + workPrice +
                ", validFromDate='" + validFromDate + '\'' +
                ", validToDate='" + validToDate + '\'' +
                ", coverPrice=" + coverPrice +
                '}';
    }
}
