package com.library.system.patterns.builder;

import com.library.system.model.LibraryItem;
import com.library.system.model.Book;
import com.library.system.model.Magazine;
import com.library.system.model.Thesis;

import java.util.Date;


public class LibraryItemBuilder {
    private int id;
    private String title;
    private String author;
    private Date year;
    private String libraryItemType;
    private String extraInfo;

    public LibraryItemBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public LibraryItemBuilder title(String title) {
        this.title = title;
        return this;
    }

    public LibraryItemBuilder author(String author) {
        this.author = author;
        return this;
    }

    public LibraryItemBuilder year(Date year) {
        this.year = year;
        return this;
    }

    public LibraryItemBuilder libraryItemType(String libraryItemType) {
        this.libraryItemType = libraryItemType;
        return this;
    }

    public LibraryItemBuilder extraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
        return this;
    }


    public LibraryItem build() {
        if (libraryItemType == null || libraryItemType.isEmpty()) {
            throw new IllegalArgumentException("Library item type must not be null or empty.");
        }

        return switch (libraryItemType.toLowerCase()) {
            case "book" ->
                    new Book(id, title, author, year, LibraryItem.LibraryItemType.valueOf(libraryItemType.toUpperCase()), Book.Status.valueOf(extraInfo));
            case "magazine" ->
                    new Magazine(id, title, author, year, LibraryItem.LibraryItemType.valueOf(libraryItemType.toUpperCase()), extraInfo);
            case "thesis" ->
                    new Thesis(id, title, author, year, LibraryItem.LibraryItemType.valueOf(libraryItemType.toUpperCase()), extraInfo);
            default -> throw new IllegalArgumentException("Unknown type: " + libraryItemType);
        };
    }

    public LibraryItemBuilder from(LibraryItem item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.author = item.getAuthor();
        this.year = item.getYear();
        this.libraryItemType = item.getType().toString();

        if (item instanceof Book book) {
            this.extraInfo = book.getStatus().name();
        } else if (item instanceof Magazine magazine) {
            this.extraInfo = magazine.getGenre();
        } else if (item instanceof Thesis thesis) {
            this.extraInfo = thesis.getUniversity();
        }
        return this;
    }
}

