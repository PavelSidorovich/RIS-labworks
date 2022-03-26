package com.sidorovich.pavel.lw9.repository;

import com.sidorovich.pavel.lw9.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByIdNumberIgnoreCase(String idNumber);

    boolean existsByIdNumberIgnoreCase(String idNumber);

}
