package ru.sfedu.hibernate.lab5.model.many_to_one;

import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Author
 */
@Entity
@Table(schema="lab5_many2one")
public class Author5 implements Serializable {
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
  @Column
 // @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
 @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  protected Set<Book5> books = new HashSet<>();


  public Author5() { };

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

  public Set<Book5> getBooks() {
    return books;
  }

  public void setBooks(Set<Book5> books) {
    this.books = books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Author5 author5 = (Author5) o;
    return id == author5.id && Objects.equals(firstName, author5.firstName)
            && Objects.equals(secondName, author5.secondName)
            && Objects.equals(lastName, author5.lastName)
            && Objects.equals(phone, author5.phone)
            && Objects.equals(email, author5.email)
            && Objects.equals(degree, author5.degree)
            && Objects.equals(organization, author5.organization)
            && ((books==null && author5.books==null) || books.stream().allMatch(e1 -> author5.books.stream().anyMatch(e2 -> e2.getId() == e1.getId())))
//           && Objects.equals(books, author5.books)
            ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, secondName, lastName, phone, email, degree, organization);
  }

  @Override
  public String toString() {
    String res = "Author5{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", degree='" + degree + '\'' +
            ", organization='" + organization + '\'';
//    Set<Long> set = books.stream().map(e1 -> e1.getId()).collect(Collectors.toSet());
//    res += set.toString();
    res += '}';
    return res;
  }
}
