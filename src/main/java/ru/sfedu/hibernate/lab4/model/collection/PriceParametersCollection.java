package ru.sfedu.hibernate.lab4.model.collection;

import ru.sfedu.hibernate.enums.CoverType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema="lab4_collection")
public class PriceParametersCollection implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private double pagePrice;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(schema="lab4_collection")
  private Set<CoverPrice> coverPrice =  new HashSet<>();
  private double workPrice;
  private String validFromDate;
  private String validToDate;

  public PriceParametersCollection() { };

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

  public Set<CoverPrice> getCoverPrice() {
    return coverPrice;
  }

  public void setCoverPrice(Set<CoverPrice> coverPrice) {
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
  public int hashCode() {
    return Objects.hash(id, pagePrice, coverPrice, workPrice, validFromDate, validToDate);
  }

  @Override
  public String toString() {
    String result = "PriceParameters{" +
            "id=" + id +
            ", pagePrice=" + pagePrice +
            ", coverPrice=" + coverPrice +
            ", workPrice=" + workPrice +
            ", validFromDate='" + validFromDate + '\'' +
            ", validToDate='" + validToDate + '\'' +
            '}';
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PriceParametersCollection that = (PriceParametersCollection) o;
    return Double.compare(that.pagePrice, pagePrice) == 0 && Double.compare(that.workPrice, workPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(coverPrice, that.coverPrice) && Objects.equals(validFromDate, that.validFromDate) && Objects.equals(validToDate, that.validToDate);
  }
}
