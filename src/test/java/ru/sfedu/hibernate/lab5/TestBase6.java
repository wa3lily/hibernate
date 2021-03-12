package ru.sfedu.hibernate.lab5;

import ru.sfedu.hibernate.lab5.model.many_to_one.Author5;
import ru.sfedu.hibernate.lab5.model.many_to_one.Book5;

import java.util.Set;

public class TestBase6 {

    public Author5 createAuthor (String firstName, String secondName, String lastName, String phone, String email, String degree, String organization){
        Author5 author = new Author5();
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        author.setLastName(lastName);
        author.setPhone(phone);
        author.setEmail(email);
        author.setDegree(degree);
        author.setOrganization(organization);
        return author;
    }

    public Book5 createBook (Author5 author, String title, int numberOfPages){
        Book5 book = new Book5();
        book.setAuthor(author);
        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        return book;
    }
}
