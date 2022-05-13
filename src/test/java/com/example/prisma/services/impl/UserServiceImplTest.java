package com.example.prisma.services.impl;

import com.example.prisma.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static com.example.prisma.data.MockData.getMockRent;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl userServiceTest;

    @BeforeEach
    void setUp() {
        userServiceTest = new UserServiceImpl(userRepository);
    }

    @Test
    void getAllUserListWithRent() {
        // when
        userServiceTest.getAllUserListWithRent();
        // then
        verify(userRepository).getUserListWithRent();
    }

    @Test
    void getAllUserListWithOutRentS() {
        // when
        userServiceTest.getAllUserListWithOutRent();
        // then
        verify(userRepository).getUserListWithOutRent();
    }

    @Test
    void getUserRentAtDate() {
        // when
        userServiceTest.getUserRentAtDate(getMockRent().getBorrowedFrom());
        // then
        verify(userRepository).findAllUserByDateRent(getMockRent().getBorrowedFrom());
    }

    @Test
    void getUserRentAtRangeOfDate() {
        // when
        ArgumentCaptor<LocalDateTime> dateTime = ArgumentCaptor.forClass(LocalDateTime.class);
        userServiceTest.getUserRentAtRangeOfDate(getMockRent().getBorrowedFrom(), getMockRent().getBorrowedTo());
        // then
        verify(userRepository).getUserListByRantingInDateRange(dateTime.capture(), dateTime.capture());
    }

    @Test
    void getUserListByPage() {
        // when
        PageRequest of = PageRequest.of(0, 10);
        userServiceTest.getUserListByPage(of);
        // then
        verify(userRepository).findAll(of);
    }
}