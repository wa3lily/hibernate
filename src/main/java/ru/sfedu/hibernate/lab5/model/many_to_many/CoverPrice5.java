package ru.sfedu.hibernate.lab5.model.many_to_many;

import org.hibernate.engine.internal.Cascade;
import ru.sfedu.hibernate.enums.CoverType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema="lab5_many2many")
public class CoverPrice5 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private CoverType coverType;
    private double price;
    @ManyToMany(mappedBy = "coverPrice", cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
    protected Set<PriceParameters5> set = new HashSet<>();

    public CoverPrice5() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<PriceParameters5> getSet() {
        return set;
    }

    public void setSet(Set<PriceParameters5> set) {
        this.set = set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverPrice5 that = (CoverPrice5) o;
        return Double.compare(that.price, price) == 0
                && Objects.equals(id, that.id)
                && coverType == that.coverType
                && (set==null && that.set == null || set.stream().allMatch(e1 -> that.set.stream().anyMatch(e2 -> e2.getId() == e1.getId())))
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coverType, price);
    }

    @Override
    public String toString() {
        return "CoverPrice5{" +
                "id=" + id +
                ", coverType=" + coverType +
                ", price=" + price +
                '}';
    }
}
