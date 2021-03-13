package ru.sfedu.hibernate.lab5.model.one_to_one;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema="lab5_one2one")
public class Author6 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String phone;
    private String email;
    private String degree;
    private String organization;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author6 author6 = (Author6) o;
        return id == author6.id && Objects.equals(firstName, author6.firstName) && Objects.equals(secondName, author6.secondName) && Objects.equals(lastName, author6.lastName) && Objects.equals(phone, author6.phone) && Objects.equals(email, author6.email) && Objects.equals(degree, author6.degree) && Objects.equals(organization, author6.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, lastName, phone, email, degree, organization);
    }

    @Override
    public String toString() {
        return "Author6{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", degree='" + degree + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
