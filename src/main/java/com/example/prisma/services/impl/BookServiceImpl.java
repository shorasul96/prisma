package com.example.prisma.services.impl;

import com.example.prisma.entities.BookEntity;
import com.example.prisma.repositories.BookRepository;
import com.example.prisma.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<BookEntity> getBookListByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<BookEntity> getBookListNotBorrowed() {
        return bookRepository.getBookNoRented();
    }
}
