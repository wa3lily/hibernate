package ru.sfedu.hibernate.lab5.model.many_to_one;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema="lab5_many2one")
public class Book5 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author5 author;
    private String title;
    private int numberOfPages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author5 getAuthor() {
        return author;
    }

    public void setAuthor(Author5 author) {
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
        Book5 book5 = (Book5) o;
        return id == book5.id && numberOfPages == book5.numberOfPages && (author == null & book5.author == null || author.getId() == book5.author.getId() ) && Objects.equals(title, book5.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, numberOfPages);
    }

    @Override
    public String toString() {
        String res = "Book5{" +
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
