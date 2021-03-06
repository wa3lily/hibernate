package ru.sfedu.hibernate.lab4.model.list;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="lab4_list")
public class PriceParametersList implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private double pagePrice;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(schema="lab4_list")
  @OrderColumn
  private List<Long> coverPrice =  new ArrayList<>();
  private double workPrice;
  private String validFromDate;
  private String validToDate;

  public PriceParametersList() { };

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
    PriceParametersList that = (PriceParametersList) o;
    return Double.compare(that.pagePrice, pagePrice) == 0 && Double.compare(that.workPrice, workPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(coverPrice, that.coverPrice) && Objects.equals(validFromDate, that.validFromDate) && Objects.equals(validToDate, that.validToDate);
  }

  public List<Long> getCoverPrice() {
    return coverPrice;
  }

  public void setCoverPrice(List<Long> coverPrice) {
    this.coverPrice = coverPrice;
  }
}
