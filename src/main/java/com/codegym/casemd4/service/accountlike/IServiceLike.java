package com.codegym.casemd4.service.accountlike;


import com.codegym.casemd4.model.AccountLike;
import com.codegym.casemd4.service.IGeneralService;

public interface IServiceLike extends IGeneralService<AccountLike> {
    AccountLike findByAccount_IdAndPost_Id(Long idAcc,Long idPost);
    public void delete(AccountLike accountLike);
}
