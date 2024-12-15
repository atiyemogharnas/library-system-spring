package com.library.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class LibraryItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String title;
    private String author;
    private Date year;
    @Enumerated(EnumType.STRING)
    private LibraryItemType type;

    public enum LibraryItemType {
        BOOK,
        MAGAZINE,
        THESIS,
        REFERENCE
    }

    public LibraryItem(int id, String title, String author, Date year, LibraryItemType type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.type = type;
    }

    public LibraryItem() {
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", type=" + type +
                '}';
    }

    public abstract void display();
}
