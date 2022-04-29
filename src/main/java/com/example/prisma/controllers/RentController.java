package com.example.prisma.controllers;

import com.example.prisma.entities.RentEntity;
import com.example.prisma.services.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rents")
public class RentController {

    private final RentService rentService;

    @GetMapping("all")
    public Page<RentEntity> getRentList(Pageable pageable) {
        return rentService.getRentListByPage(pageable);
    }
}
