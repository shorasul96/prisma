package com.example.prisma.repositories;

import com.example.prisma.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from users u where " +
            " exists(select borrower from rents where borrower = concat(u.name, ',' ,first_name))", nativeQuery = true)
    List<UserEntity> getUserListWithRent();

    @Query(value = "select * from users u where " +
            " not exists(select borrower from rents where borrower = concat(u.name, ',' ,first_name))", nativeQuery = true)
    List<UserEntity> getUserListWithOutRent();

    @Query(value = "select * from users u where " +
            "exists(select borrower from rents where borrower = concat(u.name, ',' ,first_name) and borrowed_from = :date)", nativeQuery = true)
    List<UserEntity> findAllUserByDateRent(@Param("date") String date);

    @Query(value = "select * from users u where  " +
            "  exists(select  borrower from rents where borrower = concat(u.name, ',', first_name)  " +
            "      and PARSEDATETIME(borrowed_from, 'MM/dd/YYYY') > :from " +
            "      and PARSEDATETIME(borrowed_to, 'MM/dd/YYYY') < :to ) ", nativeQuery = true)
    List<UserEntity> getUserListByRantingInDateRange(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
