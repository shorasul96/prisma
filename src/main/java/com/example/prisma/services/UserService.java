package com.example.prisma.services;

import com.example.prisma.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserEntity> getUserListByPage(Pageable pageable);

    List<UserEntity> getAllUserListWithRent();

    List<UserEntity> getAllUserListWithOutRent();

    List<UserEntity> getUserRentAtDate(String date);

    List<UserEntity>  getUserRentAtRangeOfDate(String from, String to);
}
