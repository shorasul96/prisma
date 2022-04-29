package com.example.prisma.services.impl;

import com.example.prisma.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static com.example.prisma.data.MockData.getOneUserFromList;
import static com.example.prisma.data.MockData.userListWithRent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        when(userRepository.getUserListWithOutRent()).thenReturn(Collections.emptyList());
        when(userRepository.getUserListWithRent()).thenReturn(userListWithRent());
    }

    @Test
    void getAllUserListWithRent() {
        assertEquals(11, userRepository.getUserListWithRent().size());

    }

    @Test
    @DisplayName("SUCCESS_getAllUserListWithOutRent")
    void getAllUserListWithOutRentS() {
        assertEquals(Collections.emptyList(), userRepository.getUserListWithOutRent());
    }

    @Test
    @DisplayName("FAIL_getAllUserListWithOutRent")
    void getAllUserListWithOutRentF() {
        assertNotEquals(getOneUserFromList(), userRepository.getUserListWithOutRent());
    }

    @Test
    void getUserRentAtDate() {
    }

    @Test
    void getUserRentAtRangeOfDate() {
    }
}