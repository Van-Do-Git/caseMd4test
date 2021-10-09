package com.codegym.casemd4.repository;



import com.codegym.casemd4.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFriendRepo extends JpaRepository<Friend,Long> {
    Friend findByAccount_IdAndAccount_Id(Long idAcc, Long idFriend);
}
