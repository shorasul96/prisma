package com.example.prisma.services;

import com.example.prisma.entities.RentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentService {

    Page<RentEntity> getRentListByPage(Pageable pageable);
}
