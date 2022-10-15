package com.shouvick.userservice.repository;

import com.shouvick.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "Select * from users u left join locations l on u.user_id = l.user_id where u.user_id = :id order by l.created_on desc limit 1", nativeQuery = true)
    Optional<User> findLatestById(String id);

    @Query(value = "Select * from users u left join locations l on u.user_id = :id where u.user_id = :id and l.created_on between :start_date and :end_date ", nativeQuery = true)
    Optional<User>  findDateRangeById(String id, String start_date, String end_date);

    Optional<User> findByEmail(String email);
}
