package com.library.system.patterns.strategy;

import com.library.system.model.LibraryItem;

import java.util.List;

public interface AdvancedSearchStrategy {

    List<LibraryItem> performSearch(List<LibraryItem> items, String query);
}
