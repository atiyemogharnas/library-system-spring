package com.library.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    User user;
    @ManyToOne
    Book book;
    private Date returnDate;
    private Date loanDate;

    public Borrow(User user, Book book, Date returnDate, Date loanDate) {
        this.user = user;
        this.book = book;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
    }

    public Borrow() {

    }
}
