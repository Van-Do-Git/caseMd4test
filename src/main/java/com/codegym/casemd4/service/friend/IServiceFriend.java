package com.codegym.casemd4.service.friend;


import com.codegym.casemd4.model.AccountLike;
import com.codegym.casemd4.model.Friend;
import com.codegym.casemd4.service.IGeneralService;

public interface IServiceFriend extends IGeneralService<Friend> {
    Friend findByAccount_IdAndAccount_Id(Long idAcc, Long idFriend);
}
