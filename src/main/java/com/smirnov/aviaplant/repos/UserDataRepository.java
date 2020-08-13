package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
        UserData findByUsername(String username);
}
