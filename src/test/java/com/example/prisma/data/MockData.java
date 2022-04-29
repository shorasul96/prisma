package com.example.prisma.data;

import com.example.prisma.entities.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockData {

    public static List<UserEntity> getOneUserFromList() {
        return Collections.singletonList(new UserEntity(1L, "Aexi", "Liam", "01/01/2010", null, "m"));
    }

    public static List<UserEntity> userListWithRent() {
        ArrayList<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 11; i++)
            list.add(new UserEntity(1L, "Aexi", "Liam", "01/01/2010", null, "m"));
        return list;
    }


}
