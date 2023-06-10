package com.shvaiba.liga.library.persitance;

import com.shvaiba.liga.library.persitance.entity.BorrowInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowInfoRepository extends JpaRepository<BorrowInfo, Long> {
    BorrowInfo findByBookIdAndClientIdAndIsReturned(Long bookId, Long clientId, Boolean isBorrowed);
}
