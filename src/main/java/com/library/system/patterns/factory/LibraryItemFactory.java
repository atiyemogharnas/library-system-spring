package com.library.system.patterns.factory;

import com.library.system.model.LibraryItem;
import com.library.system.patterns.builder.LibraryItemBuilder;
import com.library.system.repository.DTO.LibraryItemDTO;
import com.library.system.utils.ConvertDate;

public class LibraryItemFactory {

    public static LibraryItem createItem(String objectType, LibraryItemDTO requestDTO) {
        try {
            return new LibraryItemBuilder()
                    .title(requestDTO.getTitle())
                    .author(requestDTO.getAuthor())
                    .year(ConvertDate.convertStringToDate(requestDTO.getYear()))
                    .libraryItemType(objectType)
                    .extraInfo(requestDTO.getExtraInfo())
                    .build();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
