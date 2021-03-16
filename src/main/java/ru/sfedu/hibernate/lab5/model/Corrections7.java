package ru.sfedu.hibernate.lab5.model;

import ru.sfedu.hibernate.enums.CorrectionsStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity()
@Table()
public class Corrections7 implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private int page;
    private String textBefore;
    private String textAfter;
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order7 order;
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(unique = true)
    private Meeting7 meet;
    private CorrectionsStatus status;

    public Corrections7() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPage(int newVar) {
        page = newVar;
    }

    public int getPage() {
        return page;
    }

    public void setTextBefore(String newVar) {
        textBefore = newVar;
    }

    public String getTextBefore() {
        return textBefore;
    }

    public void setTextAfter(String newVar) {
        textAfter = newVar;
    }

    public String getTextAfter() {
        return textAfter;
    }

    public void setComment(String newVar) {
        comment = newVar;
    }

    public String getComment() {
        return comment;
    }

    public void setOrder(Order7 newVar) {
        order = newVar;
    }

    public Order7 getOrder() {
        return order;
    }

    public void setMeet(Meeting7 newVar) {
        meet = newVar;
    }

    public Meeting7 getMeet() {
        return meet;
    }

    public void setStatus(CorrectionsStatus newVar) {
        status = newVar;
    }

    public CorrectionsStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corrections7 that = (Corrections7) o;
        return id == that.id &&
                page == that.page &&
                Objects.equals(textBefore, that.textBefore) &&
                Objects.equals(textAfter, that.textAfter) &&
                Objects.equals(comment, that.comment) &&
                order.getId() == that.order.getId() &&
                (meet == null && that.meet == null || meet.getId() == that.meet.getId()) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, page, textBefore, textAfter, comment, order, meet, status);
        return result;
    }

    @Override
    public String toString() {
        String result = "Corrections{" +
                "id=" + id +
                ", page=" + page +
                ", textBefore='" + textBefore + '\'' +
                ", textAfter='" + textAfter + '\'' +
                ", comment='" + comment + '\'' +
                ", order=" + order.getId();
        try {
            result += ", meet=" + meet.getId();
        } catch (NullPointerException e) {
            result += ", meet=null";
        }
        result += ", status=" + status +
                '}';
        return result;
    }
}
