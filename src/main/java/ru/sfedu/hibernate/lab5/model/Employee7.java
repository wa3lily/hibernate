package ru.sfedu.hibernate.lab5.model;

import ru.sfedu.hibernate.enums.EmployeeType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity()
@Table()
@PrimaryKeyJoinColumn(name = "people_id")
public class Employee7 extends People7{

    private String inn;
    private String workRecordBook;
    private EmployeeType emplpyeeType;

    public Employee7 () { };

    public void setInn (String newVar) {
        inn = newVar;
    }

    public String getInn () {
        return inn;
    }

    public void setWorkRecordBook (String newVar) {
        workRecordBook = newVar;
    }

    public String getWorkRecordBook () {
        return workRecordBook;
    }

    public void setEmplpyeeType (EmployeeType newVar) {
        emplpyeeType = newVar;
    }

    public EmployeeType getEmplpyeeType () {
        return emplpyeeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee7 employee = (Employee7) o;
        return Objects.equals(inn, employee.inn) &&
                Objects.equals(workRecordBook, employee.workRecordBook) &&
                emplpyeeType == employee.emplpyeeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inn, workRecordBook, emplpyeeType);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + super.getId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", secondName='" + super.getSecondName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                "inn='" + inn + '\'' +
                ", workRecordBook='" + workRecordBook + '\'' +
                ", emplpyeeType=" + emplpyeeType +
                '}';
    }
}
