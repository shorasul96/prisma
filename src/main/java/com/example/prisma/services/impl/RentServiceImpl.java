package com.example.prisma.services.impl;

import com.example.prisma.entities.RentEntity;
import com.example.prisma.repositories.RentRepository;
import com.example.prisma.services.RentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    @Override
    public Page<RentEntity> getRentListByPage(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }
}
