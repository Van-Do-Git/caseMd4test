package com.codegym.casemd4.service.account;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.service.IGeneralService;

public interface IServiceAccount extends IGeneralService<Account>{
     Account loadUserByEmail(String email);


     boolean checkLogin(Account account);

     boolean add(Account account);

}
