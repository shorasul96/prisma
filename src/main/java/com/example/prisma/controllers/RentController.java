package com.example.prisma.controllers;

import com.example.prisma.dtos.CommonResponseDTO;
import com.example.prisma.entities.RentEntity;
import com.example.prisma.services.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rents")
public class RentController {

    private final RentService rentService;

    @GetMapping("all")
    public ResponseEntity<CommonResponseDTO> getRentList(Pageable pageable) {
        Page<RentEntity> list = rentService.getRentListByPage(pageable);
        if (!list.getContent().isEmpty())
            return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
        return ResponseEntity.noContent().build();
    }
}
