package com.library.system.controller;

import com.library.system.repository.DTO.LibraryItemDTO;
import com.library.system.service.LibraryItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarySystem/item")
@Slf4j
public class ItemController {

    @Autowired
    private LibraryItemService libraryItemService;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody LibraryItemDTO libraryItemDTO) {
        try {
            libraryItemService.createLibraryItem(libraryItemDTO);
            return ResponseEntity.ok("item is created");
        } catch (IllegalArgumentException ex) {
            log.error("Error create item: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody LibraryItemDTO libraryItemDTO) {
        try {
            libraryItemService.updateLibraryItem(libraryItemDTO);
            return ResponseEntity.ok("item is update");
        } catch (IllegalArgumentException ex) {
            log.error("Error update item: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        try {
            libraryItemService.deleteLibraryItem(id);
            return ResponseEntity.ok("item is deleted");
        } catch (IllegalArgumentException ex) {
            log.error("Error delete item: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/sortByFiled")
    public ResponseEntity<String> sort(@RequestParam String fieldName) {
        try {
            List<String> detailItems = libraryItemService.sortLibraryItemsByKeyword(fieldName);
            return ResponseEntity.ok("items is sorted" + detailItems);
        } catch (IllegalArgumentException ex) {
            log.error("Error sort for items: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/searchByValue")
    public ResponseEntity<String> search(@RequestParam String value) {
        try {
            List<String> detailItems = libraryItemService.searchLibraryItemsByValue(value);
            return ResponseEntity.ok("search value in items is " + detailItems);
        } catch (IllegalArgumentException ex) {
            log.error("Error search in  items: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
