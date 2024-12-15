package com.library.system.patterns.strategy;

import com.library.system.model.LibraryItem;

import java.util.List;

public class SearchByTitle implements AdvancedSearchStrategy {

    @Override
    public List<LibraryItem> performSearch(List<LibraryItem> items, String query) {
        return items.stream().filter(item -> item.getTitle().toLowerCase().contains(query.toLowerCase())).toList();
    }
}
