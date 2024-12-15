package com.library.system.model;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Magazine extends LibraryItem {

    private String genre;

    public Magazine(int id, String title, String author, Date year, LibraryItemType type, String genre) {
        super(id, title, author, year, type);
        this.genre = genre;
    }

    public Magazine() {
    }

    @Override
    public void display() {
        System.out.println("author : " + getAuthor());
        System.out.println("title : " + getTitle());
        System.out.println("genre : " + getGenre());
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year='" + getYear() + '\'' +
                ", genre='" + getGenre() + '\'' +
                '}';
    }
}
