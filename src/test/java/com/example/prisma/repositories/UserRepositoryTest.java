package com.example.prisma.repositories;

import com.example.prisma.entities.RentEntity;
import com.example.prisma.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.prisma.data.MockData.getMockRent;
import static com.example.prisma.data.MockData.getMockUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;
    @Autowired
    private RentRepository rentRepositoryTest;
    private UserEntity mockUser;
    private RentEntity mockRent;

    @BeforeEach
    void setUp() {
        mockUser = getMockUser();
        mockRent = getMockRent();
        userRepositoryTest.save(mockUser);
        rentRepositoryTest.save(mockRent);
    }

    @Test
    @DisplayName("Get userList with rent")
    void getUserListWithRent() {
        //when
        // it should return a user in list
        List<UserEntity> list = userRepositoryTest.getUserListWithRent();

        //then
        assertThat(list).isEqualTo(List.of(mockUser));
    }

    @Test
    @DisplayName("Get userList with rent When rents not exist")
    void getUserListWithRentWhenRentsNotExist() {
        // given
        rentRepositoryTest.deleteAll();

        //when
        // it should return empty list
        List<UserEntity> list = userRepositoryTest.getUserListWithRent();

        //then
        assertThat(list).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Get userList with rent When users not exist")
    void getUserListWithRentWhenUsersNotExist() {
        // given
        userRepositoryTest.deleteAll();

        //when
        // it should return empty list
        List<UserEntity> list = userRepositoryTest.getUserListWithRent();

        //then
        assertThat(list).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Get userList without rent")
    void getUserListWithOutRent() {
        //given
        rentRepositoryTest.deleteAll();

        //when
        List<UserEntity> list = userRepositoryTest.getUserListWithOutRent();

        //then
        assertThat(list).isEqualTo(List.of(mockUser));
    }

    @Test
    @DisplayName("Get userList without rent When users not exist")
    void getUserListWithOutRentWhenUsersNotExist() {
        //when
        List<UserEntity> list = userRepositoryTest.getUserListWithOutRent();

        //then
        assertThat(list).isEqualTo(List.of());
    }

    @Test
    @DisplayName("find all users by date rent")
    void findAllUserByDateRent() {
        //when
        List<UserEntity> list = userRepositoryTest.findAllUserByDateRent(mockRent.getBorrowedFrom());

        //then
        assertThat(list).isEqualTo(List.of(mockUser));
    }

    @Test
    @DisplayName("find all users by date rent when users not found")
    void findAllUserByDateRentWhenUsersNotFound() {
        //when
        List<UserEntity> list = userRepositoryTest.findAllUserByDateRent(mockRent.getBorrowedTo());

        //then
        assertThat(list).isEqualTo(List.of());
    }

    @Test
    @DisplayName("find all users by date range of renting")
    void getUserListByRantingInDateRange() {
        //given
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime from = LocalDate.parse(mockRent.getBorrowedFrom(), formatters).atStartOfDay();
        LocalDateTime to = LocalDate.parse(mockRent.getBorrowedTo(), formatters).atStartOfDay();

        //when
        List<UserEntity> list = userRepositoryTest.getUserListByRantingInDateRange(from, to);

        //then
        assertThat(list).isEqualTo(List.of(mockUser));

    }
}