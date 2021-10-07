package com.codegym.casemd4.service.friend;


import com.codegym.casemd4.model.Friend;
import com.codegym.casemd4.repository.IFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceFriend implements IServiceFriend{
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
}
