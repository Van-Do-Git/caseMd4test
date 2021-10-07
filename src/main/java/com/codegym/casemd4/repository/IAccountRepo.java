package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends JpaRepository<Account,Long> {
        Account findByEmail(String email);
}
