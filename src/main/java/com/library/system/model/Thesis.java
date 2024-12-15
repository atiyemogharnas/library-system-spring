package com.library.system.model;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Thesis extends LibraryItem {

    public String university;

    public Thesis(int id, String title, String author, Date year, LibraryItemType type, String university) {
        super(id, title, author, year, type);
        this.university = university;
    }

    public Thesis() {
    }

    @Override
    public void display() {
        System.out.println("author : " + getAuthor());
        System.out.println("title : " + getTitle());
        System.out.println("university : " + getUniversity());
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year='" + getYear() + '\'' +
                ", university='" + getUniversity() + '\'' +
                '}';
    }
}
