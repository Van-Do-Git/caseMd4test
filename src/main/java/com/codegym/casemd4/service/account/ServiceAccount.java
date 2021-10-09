package com.codegym.casemd4.service.account;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.repository.IAccountRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAccount implements IServiceAccount{
    @Autowired
    private IAccountRepo accountRepo;
    @Override
    public Iterable<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepo.findById(id);
    }

    @Override
    public void save(Account account) {
        accountRepo.save(account);
    }

    @Override
    public void remove(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Account loadUserByEmail(String email) {
        return accountRepo.findByEmail(email);
    }

    @Override
    public boolean checkLogin(Account account) {
        List<Account> listUser = accountRepo.findAll();
        for (Account userExist : listUser) {
            if (StringUtils.equals(account.getEmail(), userExist.getEmail())
                    && StringUtils.equals(account.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Account account) {
        List<Account> listUser = accountRepo.findAll();
        for (Account userExist : listUser) {
            if (account.getId() == userExist.getId() ||
                    StringUtils.equals(account.getEmail(), userExist.getEmail())||
                    !StringUtils.equals(account.getPassword(),account.getRe_password())) {
                return false;
            }
        }
        accountRepo.save(account);
        return true;
    }

}
