package com.library.system.repository;

import com.library.system.model.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {

    List<LibraryItem> findLibraryItemsByType(LibraryItem.LibraryItemType type);

}
