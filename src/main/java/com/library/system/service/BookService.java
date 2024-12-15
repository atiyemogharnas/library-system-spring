package com.library.system.service;

import com.library.system.model.Book;
import com.library.system.model.Borrow;
import com.library.system.model.LibraryItem;
import com.library.system.model.User;
import com.library.system.repository.BorrowRepository;
import com.library.system.repository.DTO.BorrowDTo;
import com.library.system.repository.LibraryItemRepository;
import com.library.system.repository.UserRepository;
import com.library.system.utils.ConvertDate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Service
public class BookService {

    @Autowired
    private LibraryItemRepository libraryItemRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void borrowingBook(BorrowDTo borrowDTo) {
        Book book = (Book) getLibraryItem(borrowDTo.getBookId());
        Predicate<Book.Status> existBook = bookStatus -> book.getStatus() == Book.Status.EXIST;
        if (existBook.test(book.getStatus())) {
            book.setStatus(Book.Status.BORROWED);
            User user = userRepository.findByUsername(borrowDTo.getUsername());
            Borrow borrow = new Borrow(user, book, ConvertDate.convertStringToDate(borrowDTo.getReturnDate()), ConvertDate.convertStringToDate(borrowDTo.getLoanDate()));
            borrowRepository.save(borrow);
            libraryItemRepository.save(book);
        } else {
            throw new RuntimeException("It is not possible to borrow books");
        }
    }

    public void returnedBook(BorrowDTo borrowDTo) {
        Book book = (Book) getLibraryItem(borrowDTo.getBookId());
        Predicate<Book.Status> borrowedBook = bookStatus -> book.getStatus() == Book.Status.BORROWED;
        if (borrowedBook.test(book.getStatus())) {
            book.setStatus(Book.Status.EXIST);
            assert borrowDTo.getId() != null;
            Borrow borrow = borrowRepository.findById(borrowDTo.getId()).orElse(null);
            assert borrow != null;
            borrow.setReturnDate(ConvertDate.convertStringToDate(borrowDTo.getReturnDate()));
            borrowRepository.save(borrow);
            libraryItemRepository.save(book);
        } else {
            throw new RuntimeException("This book is not borrowed");
        }
    }

    public String showStatusBook(Integer id) {
        Book book = (Book) getLibraryItem(id);
        return book.getStatus().name();
    }

    public void showTitleBook() {
        getAllBooks().forEach(item -> System.out.println(item.getTitle().toLowerCase()));
    }

    public void ShowBookWithItsCount() {
        getAllBooks().forEach(item -> {
            BiConsumer<String, Integer> showDetails = (title, counter) -> {
                if (item.getType().equals(LibraryItem.LibraryItemType.BOOK)) {
                    System.out.println("book title :" + title + " counter:" + counter);
                }
            };
            Book book = (Book) item;
            showDetails.accept(book.getTitle(), book.getNumberOfBook());
        });
    }

    public LibraryItem getLibraryItem(int id) {
        Optional<LibraryItem> item = libraryItemRepository.findById((long) id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RuntimeException("item not found");
        }
    }

    public List<LibraryItem> getAllBooks() {
        return libraryItemRepository.findLibraryItemsByType(LibraryItem.LibraryItemType.BOOK);
    }

}