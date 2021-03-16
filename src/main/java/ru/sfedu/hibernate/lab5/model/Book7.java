package ru.sfedu.hibernate.lab5.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity()
@Table()
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue( "Book" )
public class Book7 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author7 author;
    private String title;
    private int numberOfPages;

    public Book7() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author7 getAuthor() {
        return author;
    }

    public void setAuthor(Author7 author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book7 book7 = (Book7) o;
        return id == book7.id && numberOfPages == book7.numberOfPages
                && (author == null & book7.author == null || author.getId() == book7.author.getId() )
                && Objects.equals(title, book7.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author.getId(), title, numberOfPages);
    }

    @Override
    public String toString() {
        String res = "Book7{" +
                "id=" + id ;
        try{
            res += ", author=" + author.getId();
        }catch (Exception e){
            res += ", author=null" ;
        }
        res += ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
        return res;
    }
}
