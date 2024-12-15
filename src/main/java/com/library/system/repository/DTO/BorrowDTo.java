package com.library.system.repository.DTO;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowDTo {
    @Nullable
    private Integer id;
    private String username;
    private int bookId;
    @Nullable
    private String returnDate;
    @Nullable
    private String loanDate;
}
