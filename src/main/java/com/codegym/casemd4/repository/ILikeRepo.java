package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.AccountLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepo extends JpaRepository<AccountLike,Long> {
}
