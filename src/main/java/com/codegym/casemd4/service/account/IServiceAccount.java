package com.codegym.casemd4.service.account;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.service.IGeneralService;

public interface IServiceAccount extends IGeneralService<Account> {
    public Account loadUserByEmail(String email);


    public boolean checkLogin(Account account);

    public boolean add(Account account);

}
