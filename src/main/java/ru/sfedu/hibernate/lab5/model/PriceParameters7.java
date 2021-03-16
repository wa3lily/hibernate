package ru.sfedu.hibernate.lab5.model;

import ru.sfedu.hibernate.lab5.model.many_to_many.CoverPrice5;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity()
@Table()
public class PriceParameters7 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double pagePrice;
    private double workPrice;
    private String validFromDate;
    private String validToDate;
    @ManyToMany(fetch = FetchType.EAGER /*, cascade = {CascadeType.PERSIST, CascadeType.PERSIST}*/ )
    @JoinTable()
    protected Set<CoverPrice7> coverPrice =  new HashSet<>();

    public PriceParameters7() { };

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

    public Set<CoverPrice7> getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(Set<CoverPrice7> coverPrice) {
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
        PriceParameters7 that = (PriceParameters7) o;
        return Double.compare(that.pagePrice, pagePrice) == 0
                && Double.compare(that.workPrice, workPrice) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(validFromDate, that.validFromDate)
                && Objects.equals(validToDate, that.validToDate)
                && (coverPrice == null && that.coverPrice == null || coverPrice.stream().allMatch(e1 -> that.coverPrice.stream().anyMatch(e2 -> e2.getId() == e1.getId())))
//                && Objects.equals(coverPrice, that.coverPrice)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pagePrice, workPrice, validFromDate, validToDate);
    }

    @Override
    public String toString() {
        return "PriceParameters5{" +
                "id=" + id +
                ", pagePrice=" + pagePrice +
                ", workPrice=" + workPrice +
                ", validFromDate='" + validFromDate + '\'' +
                ", validToDate='" + validToDate + '\'' +
//                ", coverPrice=" + coverPrice +
                '}';
    }
}
