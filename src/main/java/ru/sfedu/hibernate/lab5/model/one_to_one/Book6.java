package ru.sfedu.hibernate.lab5.model.one_to_one;

import ru.sfedu.hibernate.lab5.model.one_to_one.Author6;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema="lab5_one2one")
public class Book6 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(unique = true)
    private Author6 author;
    private String title;
    private int numberOfPages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author6 getAuthor() {
        return author;
    }

    public void setAuthor(Author6 author) {
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
        Book6 book6 = (Book6) o;
        return id == book6.id && numberOfPages == book6.numberOfPages
                && (author==null && book6.author==null || author.getId()==book6.author.getId())
//                && Objects.equals(author, book6.author)
                && Objects.equals(title, book6.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, numberOfPages);
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
