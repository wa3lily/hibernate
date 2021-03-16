package ru.sfedu.hibernate.lab5.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity()
@Table()
public class Meeting7 implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String meetDate;
    private boolean authorAgreement;
    private boolean editorAgreement;

    public Meeting7 () { };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMeetDate (String newVar) {
        meetDate = newVar;
    }

    public String getMeetDate () {
        return meetDate;
    }

    public void setAuthorAgreement (boolean newVar) {
        authorAgreement = newVar;
    }

    public boolean getAuthorAgreement () {
        return authorAgreement;
    }

    public void setEditorAgreement (boolean newVar) {
        editorAgreement = newVar;
    }

    public boolean getEditorAgreement () {
        return editorAgreement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting7 meeting = (Meeting7) o;
        return id == meeting.id &&
                authorAgreement == meeting.authorAgreement &&
                editorAgreement == meeting.editorAgreement &&
                Objects.equals(meetDate, meeting.meetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetDate, authorAgreement, editorAgreement);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", meetDate='" + meetDate + '\'' +
                ", authorAgreement=" + authorAgreement +
                ", editorAgreement=" + editorAgreement +
                '}';
    }
}
