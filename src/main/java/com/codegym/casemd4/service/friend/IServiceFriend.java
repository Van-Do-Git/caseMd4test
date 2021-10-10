package com.codegym.casemd4.service.friend;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.model.Friend;
import com.codegym.casemd4.service.IGeneralService;

import java.util.List;

public interface IServiceFriend extends IGeneralService<Friend> {
    Friend findByAccount_IdAndAccount_Id(Account account, Account friend);
    List<Friend> findAllByIdAcc(Account account, Boolean status1, Account friend, Boolean status2);
    List<Friend> findFriendByIdAcc(Account account, Boolean status1);
}
