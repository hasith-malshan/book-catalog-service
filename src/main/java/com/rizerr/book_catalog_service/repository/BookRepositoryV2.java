package com.rizerr.book_catalog_service.repository;

import com.rizerr.book_catalog_service.entity.BookV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryV2 extends JpaRepository<BookV2, Integer> {
}
