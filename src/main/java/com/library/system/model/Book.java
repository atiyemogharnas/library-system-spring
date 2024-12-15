package com.library.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class Book extends LibraryItem  {

    @Enumerated(EnumType.STRING)
    private Status status;
    private final Integer numberOfBook = 0;

    public Book() {
    }

    @Override
    public void display() {
        System.out.println("author : " + getAuthor());
        System.out.println("title : " + getTitle());
        System.out.println("status : " + getStatus());
    }

    public Book(int id, String title, String author, Date year, LibraryItemType type, Status status) {
        super(id, title, author, year, type);
        this.status = status;

    }

    public enum Status {
        EXIST,
        BORROWED,
        BANNED
    }


    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year='" + getYear() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}
