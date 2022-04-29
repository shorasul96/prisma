package com.example.prisma.repositories;

import com.example.prisma.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query(value = "SELECT * FROM books where " +
            " not exists(select book from rents where book = title)", nativeQuery = true)
    List<BookEntity> getBookNoRented();
}
