package ru.sfedu.hibernate.lab2.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AnotherTestEntity implements Serializable {
    private String descriotion;

    public String getDescriotion() {
        return descriotion;
    }

    public void setDescriotion(String descriotion) {
        this.descriotion = descriotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnotherTestEntity that = (AnotherTestEntity) o;
        return Objects.equals(descriotion, that.descriotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriotion);
    }

    @Override
    public String toString() {
        return "AnotherTestEntity{" +
                "descriotion='" + descriotion + '\'' +
                '}';
    }
}
