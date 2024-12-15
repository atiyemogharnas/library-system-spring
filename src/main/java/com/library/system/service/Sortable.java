package com.library.system.service;

import java.util.List;

public interface Sortable {

    List<String> sortLibraryItemsByKeyword(String sortFieldName);
}
