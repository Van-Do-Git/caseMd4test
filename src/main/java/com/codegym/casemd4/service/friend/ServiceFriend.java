package com.codegym.casemd4.service.friend;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.model.AccountLike;
import com.codegym.casemd4.model.Friend;
import com.codegym.casemd4.repository.IFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceFriend implements IServiceFriend {
    @Autowired
    private IFriendRepo friendRepo;

    @Override
    public Iterable<Friend> findAll() {
        return friendRepo.findAll();
    }

    @Override
    public Optional<Friend> findById(Long id) {
        return friendRepo.findById(id);
    }

    @Override
    public void save(Friend friend) {
        friendRepo.save(friend);
    }

    @Override
    public void remove(Long id) {
        friendRepo.deleteById(id);
    }


    @Override
    public Friend findByAccount_IdAndAccount_Id(Account account, Account friend) {
        return friendRepo.findByAccount_IdAndAccount_Id(account,friend);
    }

    @Override
    public List<Friend> findAllByIdAcc(Account account, Boolean status1, Account friend, Boolean status2) {
        return friendRepo.findAllByIdAcc(account,status1,friend,status2);
    }

    @Override
    public List<Friend> findFriendByIdAcc(Account account, Boolean status1) {
        return friendRepo.findFriendByIdAcc(account,status1);
    }


}
