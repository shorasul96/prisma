package com.example.prisma.controllers;


import com.example.prisma.dtos.CommonResponseDTO;
import com.example.prisma.entities.UserEntity;
import com.example.prisma.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.util.Locale.forLanguageTag;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final String BUNDLE_NAME = "message";
    private final String INVALID_FORMAT = "invalid_date_format";

    @Value("${regex.date}")
    private String DATE_PATTERN;


    @GetMapping("all")
    public ResponseEntity<CommonResponseDTO> getUserList(Pageable pageable) {
        Page<UserEntity> list = userService.getUserListByPage(pageable);
        if (!list.getContent().isEmpty())
            return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("with-rent")
    public ResponseEntity<CommonResponseDTO> getAllUserListWithRent() {
        List<UserEntity> list = userService.getAllUserListWithRent();
        if (!list.isEmpty())
            return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("without-rent")
    public ResponseEntity<CommonResponseDTO> getAllUserListWithOutRent() {
        List<UserEntity> list = userService.getAllUserListWithOutRent();
        if (!list.isEmpty())
            return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("rented-at-date")
    public ResponseEntity<CommonResponseDTO> getAllUsersRentingAtGivenDate(
            @RequestHeader(defaultValue = "en") String language,
            @RequestParam(defaultValue = "") String date) {
        if (Pattern.compile(DATE_PATTERN)
                .matcher(date.trim())
                .matches()) {
            List<UserEntity> list = userService.getUserRentAtDate(date);
            if (!list.isEmpty())
                return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(ResourceBundle.getBundle(BUNDLE_NAME, forLanguageTag(language))
                        .getString(INVALID_FORMAT), BAD_REQUEST.value()));
    }

    @GetMapping("rented-at-date-range")
    public ResponseEntity<Object> getAllUsersRentingAtRangeOfDates(
            @RequestHeader(defaultValue = "en") String language,
            @RequestParam(defaultValue = "") String from,
            @RequestParam(defaultValue = "") String to) {
        if (Pattern.compile(DATE_PATTERN).matcher(from.trim()).matches()
                && Pattern.compile(DATE_PATTERN).matcher(to.trim()).matches()) {
            List<UserEntity> list = userService.getUserRentAtRangeOfDate(from, to);
            if (!list.isEmpty())
                return ResponseEntity.ok(new CommonResponseDTO(list, OK.name(), OK.value()));
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest()
                .body(new CommonResponseDTO(ResourceBundle.getBundle(BUNDLE_NAME, forLanguageTag(language))
                        .getString(INVALID_FORMAT), BAD_REQUEST.value()));
    }
}
