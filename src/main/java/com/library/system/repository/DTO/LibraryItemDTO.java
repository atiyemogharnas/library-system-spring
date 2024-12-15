package com.library.system.repository.DTO;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LibraryItemDTO {

    @Nullable
    private Integer id;
    private String title;
    private String author;
    private String year;
    private String libraryItemType;
    private String username;
    private Date returnDate;
    private Date loanDate;
    private String extraInfo;

}
