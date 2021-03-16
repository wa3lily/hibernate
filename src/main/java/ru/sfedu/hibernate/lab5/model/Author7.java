package ru.sfedu.hibernate.lab5.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity()
@Table()
@PrimaryKeyJoinColumn(name = "people_id")
public class Author7 extends People7{
    private String email;
    private String degree;
    private String organization;
    @Column(nullable = true)
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    protected Set<Book7> books = new HashSet<>();

    public Author7 () { };

    public void setEmail (String newVar) {
        email = newVar;
    }

    public String getEmail () {
        return email;
    }

    public void setDegree (String newVar) {
        degree = newVar;
    }

    public String getDegree () {
        return degree;
    }

    public void setOrganization (String newVar) {
        organization = newVar;
    }

    public String getOrganization () {
        return organization;
    }

    public Set<Book7> getBooks() {
        return books;
    }

    public void setBooks(Set<Book7> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author7 author = (Author7) o;
        return Objects.equals(email, author.email) &&
                Objects.equals(degree, author.degree) &&
                Objects.equals(organization, author.organization)
                && ((books==null && author.books==null) || books.stream().allMatch(e1 -> author.books.stream().anyMatch(e2 -> e2.getId() == e1.getId())))
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, degree, organization);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + super.getId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", secondName='" + super.getSecondName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", email='" + email + '\'' +
                ", degree='" + degree + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
