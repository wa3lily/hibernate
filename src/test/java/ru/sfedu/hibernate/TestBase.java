package ru.sfedu.hibernate;

import ru.sfedu.hibernate.lab3.MappedSuperclass.model.*;
import ru.sfedu.hibernate.enums.BookStatus;
import ru.sfedu.hibernate.enums.CorrectionsStatus;
import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.enums.EmployeeType;

import java.util.List;

public class TestBase {

    public People createPeople (String firstName, String secondName, String lastName, String phone){
        People people = new People();
        people.setFirstName(firstName);
        people.setSecondName(secondName);
        people.setLastName(lastName);
        people.setPhone(phone);
        return people;
    }

    public Author createAuthor (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
        Author author = new Author();
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        author.setLastName(lastName);
        author.setPhone(phone);
        author.setEmail(email);
        author.setDegree(degree);
        author.setOrganization(organization);
        return author;
    }

    public Employee createEmployee (String firstName, String secondName, String lastName, String phone, String inn, String workRecordBook, EmployeeType emplpyeeType){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setLastName(lastName);
        employee.setPhone(phone);
        employee.setInn(inn);
        employee.setWorkRecordBook(workRecordBook);
        employee.setEmplpyeeType(emplpyeeType);
        return employee;
    }

}
