package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFriendRepo extends JpaRepository<Friend, Long> {
    @Query("select f from Friend f where f.account = ?1 and  f.friend = ?2")
    Friend findByAccount_IdAndAccount_Id(Account account, Account friend);


    @Query("select f from Friend f where f.account = ?1 and f.status = ?2 or f.friend = ?3 and f.status = ?4")
    List<Friend> findAllByIdAcc(Account account, Boolean status1, Account friend, Boolean status2);

    @Query("select f from Friend f where f.friend = ?1 and f.status = ?2")
    List<Friend> findFriendByIdAcc(Account account, Boolean status1);
}
