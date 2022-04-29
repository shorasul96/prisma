package com.example.prisma.controllers;


import com.example.prisma.entities.UserEntity;
import com.example.prisma.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final HashMap<Object, Object> message = new HashMap<>();

    @PostConstruct
    void init() {
        message.put("message", "Wrong input format! Recommended MM/dd/YYYY");
    }

    @Value("${regex.date}")
    private String DATE_PATTERN;


    @GetMapping("all")
    public Page<UserEntity> getUserList(Pageable pageable) {
        return userService.getUserListByPage(pageable);
    }

    @GetMapping("with-rent")
    List<UserEntity> getAllUserListWithRent() {
        return userService.getAllUserListWithRent();
    }

    @GetMapping("without-rent")
    public List<UserEntity> getAllUserListWithOutRent() {
        return userService.getAllUserListWithOutRent();
    }

    @GetMapping("rented-at-date")
    public ResponseEntity<Object> getAllUsersRentingAtGivenDate(@RequestParam(defaultValue = "") String date) {
        if (Pattern.compile(DATE_PATTERN)
                .matcher(date.trim())
                .matches()) {
            return new ResponseEntity<>(userService.getUserRentAtDate(date), HttpStatus.OK);
        }

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        // here I could convert all response body to one format like a CommonResponse class
        // to show content with all statuses
    }

    @GetMapping("rented-at-date-range")
    public ResponseEntity<Object> getAllUsersRentingAtRangeOfDates(@RequestParam(defaultValue = "") String from, @RequestParam(defaultValue = "") String to) {
        if (Pattern.compile(DATE_PATTERN).matcher(from.trim()).matches()
                && Pattern.compile(DATE_PATTERN).matcher(to.trim()).matches()) {
            return new ResponseEntity<>(userService.getUserRentAtRangeOfDate(from, to), HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        // here I could convert all response body to one format like a CommonResponse class
        // to show content with all statuses
    }
}
