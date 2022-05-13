package com.example.prisma.controllers;

import com.example.prisma.entities.UserEntity;
import com.example.prisma.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.example.prisma.data.MockData.getMockRent;
import static com.example.prisma.data.MockData.getMockUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceTest;

    @Test
    void getUserList() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 1);
        when(userServiceTest.getUserListByPage(pageRequest))
                .thenReturn(new PageImpl<>(List.of(getMockUser())));

        //when
        mockMvc.perform(get("/api/v1/users/all?page=0&size=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        //then
        verify(userServiceTest).getUserListByPage(pageRequest);
    }

    @Test
    void getUserListWhenNoContent() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 1);
        when(userServiceTest.getUserListByPage(pageRequest))
                .thenReturn(new PageImpl<>(List.of()));

        //when
        mockMvc.perform(get("/api/v1/users/all?page=0&size=1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //then
        verify(userServiceTest).getUserListByPage(pageRequest);
    }

    @Test
    void getAllUserListWithRent() throws Exception {
        //given
        when(userServiceTest.getAllUserListWithRent())
                .thenReturn(List.of(getMockUser()));

        //when
        mockMvc.perform(get("/api/v1/users/with-rent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(userServiceTest).getAllUserListWithRent();
    }

    @Test
    void getAllUserListWithRentWhenNoContent() throws Exception {
        //given
        when(userServiceTest.getAllUserListWithRent())
                .thenReturn(List.of());

        //when
        mockMvc.perform(get("/api/v1/users/with-rent"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //then
        verify(userServiceTest).getAllUserListWithRent();
    }

    @Test
    void getAllUserListWithOutRent() throws Exception {
        //given
        when(userServiceTest.getAllUserListWithOutRent())
                .thenReturn(List.of(getMockUser()));

        //when
        mockMvc.perform(get("/api/v1/users/without-rent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(userServiceTest).getAllUserListWithOutRent();
    }

    @Test
    void getAllUserListWithOutRentWhenNoContent() throws Exception {
        //given
        when(userServiceTest.getAllUserListWithOutRent())
                .thenReturn(List.of());

        //when
        mockMvc.perform(get("/api/v1/users/without-rent"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //then
        verify(userServiceTest).getAllUserListWithOutRent();
    }

    @Test
    void getAllUsersRentingAtGivenDate() throws Exception {
        //given
        when(userServiceTest.getUserRentAtDate(getMockRent().getBorrowedFrom()))
                .thenReturn(List.of(getMockUser()));

        //when
        mockMvc.perform(get("/api/v1/users/rented-at-date?date=" + getMockRent().getBorrowedFrom())
                        .header("Accept-Language", "de"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(userServiceTest).getUserRentAtDate(getMockRent().getBorrowedFrom());
    }

    @Test
    void getAllUsersRentingAtGivenDateWhenNoContent() throws Exception {
        //given
        when(userServiceTest.getUserRentAtDate(getMockRent().getBorrowedFrom()))
                .thenReturn(List.of());

        //when
        mockMvc.perform(get("/api/v1/users/rented-at-date?date=" + getMockRent().getBorrowedFrom())
                        .header("Accept-Language", "de"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //then
        verify(userServiceTest).getUserRentAtDate(getMockRent().getBorrowedFrom());
    }

    @Test
    void getAllUsersRentingAtGivenDateWhenBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/users/rented-at-date?date=" + "foo")
                        .header("Accept-Language", "de"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllUsersRentingAtRangeOfDates() throws Exception {
        //given
        when(userServiceTest.getUserRentAtRangeOfDate(getMockRent().getBorrowedFrom(), getMockRent().getBorrowedTo()))
                .thenReturn(List.of(getMockUser()));

        //when
        mockMvc.perform(get("/api/v1/users/rented-at-date-range?from="
                        + getMockRent().getBorrowedFrom() + "&to="
                        + getMockRent().getBorrowedTo())
                        .header("Accept-Language", "de"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(userServiceTest).getUserRentAtRangeOfDate(getMockRent().getBorrowedFrom(), getMockRent().getBorrowedTo());
    }

    @Test
    void getAllUsersRentingAtRangeOfDatesWhenNoContent() throws Exception {
        //given
        when(userServiceTest.getUserRentAtRangeOfDate(getMockRent().getBorrowedFrom(), getMockRent().getBorrowedTo()))
                .thenReturn(List.of());

        //when
        mockMvc.perform(get("/api/v1/users/rented-at-date-range?from="
                        + getMockRent().getBorrowedFrom() + "&to="
                        + getMockRent().getBorrowedTo())
                        .header("Accept-Language", "de"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //then
        verify(userServiceTest).getUserRentAtRangeOfDate(getMockRent().getBorrowedFrom(), getMockRent().getBorrowedTo());
    }

    @Test
    void getAllUsersRentingAtRangeOfDatesWhenBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/users/rented-at-date-range?from="
                        + "foo" + "&to="
                        + "foo")
                        .header("Accept-Language", "de"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}