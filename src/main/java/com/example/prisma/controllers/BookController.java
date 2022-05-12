package com.example.prisma.controllers;

import com.example.prisma.dtos.CommonResponseDTO;
import com.example.prisma.entities.BookEntity;
import com.example.prisma.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("all")
    public ResponseEntity<CommonResponseDTO> getBookList(Pageable pageable) {
        Page<BookEntity> list = bookService.getBookListByPage(pageable);
        if (!list.getContent().isEmpty())
            return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
        return ResponseEntity.noContent().build();
    }


    @GetMapping("not-borrowed")
    public ResponseEntity<CommonResponseDTO> getBookListNotBorrowed() {
        List<BookEntity> list = bookService.getBookListNotBorrowed();
        if (!list.isEmpty())
            return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
        return ResponseEntity.noContent().build();
    }
}
