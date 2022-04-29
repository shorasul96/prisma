package com.example.prisma.controllers;

import com.example.prisma.entities.BookEntity;
import com.example.prisma.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("all")
    public Page<BookEntity> getBookList(Pageable pageable) {
        return bookService.getBookListByPage(pageable);
    }


    @GetMapping("not-borrowed")
    public List<BookEntity> getBookListNotBorrowed() {
        return bookService.getBookListNotBorrowed();
    }
}
