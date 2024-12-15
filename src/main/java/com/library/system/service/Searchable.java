package com.library.system.service;

import java.util.List;

public interface Searchable {

    List<String> searchLibraryItemsByValue(String value);

}
