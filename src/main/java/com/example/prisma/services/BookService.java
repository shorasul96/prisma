package com.example.prisma.services;

import com.example.prisma.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<BookEntity> getBookListByPage(Pageable pageable);

    List<BookEntity> getBookListNotBorrowed();

}
