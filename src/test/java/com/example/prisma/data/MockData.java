package com.example.prisma.data;

import com.example.prisma.entities.RentEntity;
import com.example.prisma.entities.UserEntity;

public class MockData {

    public static UserEntity getMockUser() {
        return new UserEntity(null, "Aexi", "Liam", "01/01/2010", "", "m");
    }

    public static RentEntity getMockRent() {
        return new RentEntity(null, "Aexi,Liam", "Identity & Violence", "06/23/2020", "07/19/2020");
    }

}
