package ru.sfedu.hibernate.lab3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.hibernate.enums.EmployeeType;
import ru.sfedu.hibernate.lab3.SingleTable.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SingleTableTest extends TestBase3 {

    private static Logger log = LogManager.getLogger(SingleTableTest.class);
    Lab3Provider instance = new Lab3Provider();

    @Test
    public void TestSaveSuccess() {
        log.info("saveSuccess");
        Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Long resultA = instance.save(author);
        Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
        Long resultE = instance.save(employee);
        Author author1 = instance.getById(Author.class, resultA).get();
        Employee employee1 = instance.getById(Employee.class, resultE).get();
        log.debug(author1);
        log.debug(employee1);
        assertEquals(author, author1);
        assertEquals(employee, employee1);
    }

    @Test
    public void TestGetByIdFail() {
        log.info("GetByIdFail");
        Optional<Author> entity1 = instance.getById(Author.class, 200L);
        assertNull(entity1.orElse(null));
    }

    @Test
    public void TestDeleteSuccess() {
        log.info("DeleteSuccess");
        Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Long resultA = instance.save(author);
        Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
        Long resultE = instance.save(employee);
        Author author1 = instance.getById(Author.class, resultA).get();
        Employee employee1 = instance.getById(Employee.class, resultE).get();
        log.debug(author1);
        log.debug(employee1);
        assertEquals(author, author1);
        assertEquals(employee, employee1);
        log.debug(instance.delete(Author.class,resultA));
        log.debug(instance.delete(Employee.class,resultE));
        Author author2 = instance.getById(Author.class, resultA).orElse(null);
        Employee employee2 = instance.getById(Employee.class, resultE).orElse(null);
        assertNull(author2);
        assertNull(employee2);
    }

    @Test
    public void TestDeleteFail() {
        log.info("DeleteFail");
        log.info("DeleteSuccess");
        Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Long resultA = instance.save(author);
        Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
        Long resultE = instance.save(employee);
        log.debug(instance.delete(Author.class,resultE));
        Author author1 = instance.getById(Author.class, resultA).get();
        Employee employee1 = instance.getById(Employee.class, resultE).get();
        assertEquals(author, author1);
        assertEquals(employee, employee1);
    }

    @Test
    public void TestUpdateSuccess(){
        log.info("UpdateSuccess");
        Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Long resultA = instance.save(author);
        Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
        Long resultE = instance.save(employee);
        Author author1 = instance.getById(Author.class, resultA).get();
        Employee employee1 = instance.getById(Employee.class, resultE).get();
        assertEquals(author, author1);
        assertEquals(employee, employee1);
        author1.setPhone("88000000000");
        employee1.setEmplpyeeType(EmployeeType.ADMIN);
        log.debug(instance.update(author1));
        log.debug(instance.update(employee1));
        Author author2 = instance.getById(Author.class, resultA).orElse(null);
        Employee employee2 = instance.getById(Employee.class, resultE).orElse(null);
        assertEquals(author2, author1);
        assertEquals(employee2, employee1);
    }

    @Test
    public void TestUpdateFail(){
        log.info("UpdateFail");
        Long id1 = 203L;
        Long id2 = 204L;
        Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        author.setId(id1);
        Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
        employee.setId(id2);
        instance.update(author);
        instance.update(employee);
        Author author1 = instance.getById(Author.class, id1).orElse(null);
        Employee employee1 = instance.getById(Employee.class, id2).orElse(null);
        assertNull(author1);
        assertNull(employee1);
    }

    @Test
    public void TestReadSuccess(){
        log.info("ReadSuccess");
        instance.truncateTable(Author.class);
        instance.truncateTable(Employee.class);
        Author author = createAuthor("Виктор","Иванович","Ткач","83456789012", "tkach@gmail.com", "docent", "Donstu");
        Long resultA = instance.save(author);
        Employee employee = createEmployee("Иван","Иванович","Иванов","81234567890", "123456789012","1234567", EmployeeType.CHIEF);
        Long resultE = instance.save(employee);
        Author author1 = instance.getById(Author.class, resultA).get();
        Employee employee1 = instance.getById(Employee.class, resultE).get();
        List<Author> listAuthor = new ArrayList<>();
        listAuthor.add(author1);
        //assertEquals(listAuthor, instance.read(Author.class));
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee.add(employee1);
        //assertEquals(listEmployee, instance.read(Employee.class));
    }

    @Test
    public void TestReadFail(){
        log.info("ReadFail");
        instance.truncateTable(Author.class);
        instance.truncateTable(Employee.class);
        assertTrue(instance.read(Author.class).isEmpty());
        assertTrue(instance.read(Employee.class).isEmpty());
    }

}