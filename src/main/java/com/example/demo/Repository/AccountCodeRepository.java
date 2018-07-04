package com.example.demo.Repository;

import com.example.demo.Domain.AccountCode;
import com.example.demo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCodeRepository extends JpaRepository<AccountCode, Integer> {
    AccountCode findByUser(User user);
}
