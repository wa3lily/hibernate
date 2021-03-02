package ru.sfedu.hibernate.lab3.MappedSuperclass.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class People
 */
@MappedSuperclass
public class People implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String secondName;
  private String lastName;
  private String phone;

  public People () { };

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setFirstName (String newVar) {
    firstName = newVar;
  }

  public String getFirstName () {
    return firstName;
  }

  public void setSecondName (String newVar) {
    secondName = newVar;
  }

  public String getSecondName () {
    return secondName;
  }

  public void setLastName (String newVar) {
    lastName = newVar;
  }

  public String getLastName () {
    return lastName;
  }

  public void setPhone (String newVar) {
    phone = newVar;
  }

  public String getPhone () {
    return phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    People people = (People) o;
    return id == people.id &&
            Objects.equals(firstName, people.firstName) &&
            Objects.equals(secondName, people.secondName) &&
            Objects.equals(lastName, people.lastName) &&
            Objects.equals(phone, people.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, secondName, lastName, phone);
  }

  @Override
  public String toString() {
    return "People{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phone='" + phone + '\'' +
            '}';
  }
}
