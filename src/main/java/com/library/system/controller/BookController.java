package com.library.system.controller;

import com.library.system.repository.DTO.BorrowDTo;
import com.library.system.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowDTo borrowDTo) {
        try {
            bookService.borrowingBook(borrowDTo);
            return ResponseEntity.ok("borrowed book successfully");
        } catch (IllegalArgumentException ex) {
            log.error("Error borrow book: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowDTo borrowDTo) {
        try {
            bookService.returnedBook(borrowDTo);
            return ResponseEntity.ok("return book successfully");
        } catch (IllegalArgumentException ex) {
            log.error("Error return book: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> statusBook(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(bookService.showStatusBook(id));
        } catch (IllegalArgumentException ex) {
            log.error("Error status book: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
