package com.library.system.repository;

import com.library.system.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    @Query("select l.author, l.title, b.loanDate, b.returnDate, " +
            "case when b.returnDate is null then 'BORROWED' else 'AVAILABLE' end as status " +
            "from Borrow b " +
            "join LibraryItem l on b.book.id = l.id " +
            "where b.user.id = :userId")
    Object[] allLoansParticularUser(Integer userId);

    @Query(value = "SELECT AVG(mycount) FROM (SELECT user_id, COUNT(*) AS mycount FROM Borrow GROUP BY user_id) AS subquery", nativeQuery = true)
    Double findAverageBorrowCount();

}
