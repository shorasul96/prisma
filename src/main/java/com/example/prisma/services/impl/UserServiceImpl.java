package com.example.prisma.services.impl;

import com.example.prisma.entities.UserEntity;
import com.example.prisma.repositories.UserRepository;
import com.example.prisma.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserEntity> getUserListByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<UserEntity> getAllUserListWithRent() {
        return userRepository.getUserListWithRent();
    }

    @Override
    public List<UserEntity> getAllUserListWithOutRent() {
        return userRepository.getUserListWithOutRent();
    }

    @Override
    public List<UserEntity> getUserRentAtDate(String date) {
        return userRepository.findAllUserByDateRent(date);
    }

    @Override
    public List<UserEntity> getUserRentAtRangeOfDate(String from, String to) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return userRepository.getUserListByRantingInDateRange(
                LocalDate.parse(from, formatters).atStartOfDay(),
                LocalDate.parse(to, formatters).atStartOfDay());
    }
}
