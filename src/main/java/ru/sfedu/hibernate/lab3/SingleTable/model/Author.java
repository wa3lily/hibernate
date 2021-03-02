package ru.sfedu.hibernate.lab3.SingleTable.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class Author
 */
@Entity(name = "Author3")
@Table(schema="lab3_single_table")
@DiscriminatorValue( "Author" )
public class Author extends People {

  private String email;
  private String degree;
  private String organization;

  public Author () { };

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Author author = (Author) o;
    return Objects.equals(email, author.email) &&
            Objects.equals(degree, author.degree) &&
            Objects.equals(organization, author.organization);
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
