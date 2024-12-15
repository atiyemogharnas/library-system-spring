package com.library.system.service;

import com.library.system.model.Book;
import com.library.system.model.Borrow;
import com.library.system.model.LibraryItem;
import com.library.system.model.User;
import com.library.system.patterns.builder.LibraryItemBuilder;
import com.library.system.patterns.factory.LibraryItemFactory;
import com.library.system.repository.BorrowRepository;
import com.library.system.repository.DTO.LibraryItemDTO;
import com.library.system.repository.LibraryItemRepository;
import com.library.system.repository.UserRepository;
import com.library.system.utils.ConvertDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryItemService implements Searchable, Sortable {

    @Autowired
    private LibraryItemRepository libraryItemRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> searchLibraryItemsByValue(String value) {
        List<String> detailItems = new ArrayList<>();
        libraryItemRepository.findAll().stream().filter(libraryItem -> libraryItem.getTitle().toLowerCase().contains(value.toLowerCase()) ||
                libraryItem.getAuthor().toLowerCase().contains(value.toLowerCase())).forEach(libraryItem -> {
            detailItems.add(libraryItem.toString());
        });
        return detailItems;
    }

    @Override
    public List<String> sortLibraryItemsByKeyword(String sortFieldName) {
        List<String> detailItems = new ArrayList<>();
        Comparator<LibraryItem> comparator;
        if ("title".equalsIgnoreCase(sortFieldName)) {
            comparator = Comparator.comparing(LibraryItem::getTitle);
        } else if ("author".equals(sortFieldName)) {
            comparator = Comparator.comparing(LibraryItem::getAuthor);
        } else {
            comparator = Comparator.comparing(LibraryItem::getYear);
        }
        libraryItemRepository.findAll().stream().sorted(comparator).forEach(libraryItem -> {
            detailItems.add(libraryItem.toString());
        });
        return detailItems;
    }

    public void createLibraryItem(LibraryItemDTO requestDTO) {
        LibraryItem item = LibraryItemFactory.createItem(requestDTO.getLibraryItemType(), requestDTO);
        libraryItemRepository.save(item);
    }

    public void deleteLibraryItem(int id) {
        LibraryItem item = getLibraryItem(id);
        libraryItemRepository.delete(item);
    }

    public void updateLibraryItem(LibraryItemDTO requestDTO) {
        if (requestDTO.getId() == null) {
            throw new RuntimeException("id is not correct or not found");
        }
        LibraryItem oldItem = getLibraryItem(requestDTO.getId());
        LibraryItem updatedItem = null;
        if (requestDTO.getLibraryItemType().toUpperCase().equals(LibraryItem.LibraryItemType.BOOK.name())) {
            Book book = (Book) oldItem;
            if (book.getStatus() == Book.Status.BORROWED) {
                User user = userRepository.findByUsername(requestDTO.getUsername());
                Borrow borrow = new Borrow(user, book, requestDTO.getReturnDate(), requestDTO.getLoanDate());
                borrowRepository.save(borrow);
            }

        }
        updatedItem = new LibraryItemBuilder().from(oldItem)
                .title(requestDTO.getTitle())
                .author(requestDTO.getAuthor())
                .year(ConvertDate.convertStringToDate(requestDTO.getYear()))
                .extraInfo(requestDTO.getExtraInfo())
                .build();

        assert updatedItem != null;
        libraryItemRepository.save(updatedItem);
    }

    public LibraryItem getLibraryItem(int id) {
        Optional<LibraryItem> item = libraryItemRepository.findById((long) id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RuntimeException("item not found");
        }
    }
}
