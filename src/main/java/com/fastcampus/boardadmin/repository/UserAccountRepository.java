package com.fastcampus.boardadmin.repository;

import com.fastcampus.boardadmin.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}