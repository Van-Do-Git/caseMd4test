package com.codegym.casemd4.service.approle;


import com.codegym.casemd4.model.AppRole;
import com.codegym.casemd4.repository.IAppRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceAppRole implements IServiceAppRole{
    @Autowired
    private IAppRoleRepo appRoleRepo;
    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepo.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return appRoleRepo.findById(id);
    }

    @Override
    public void save(AppRole appRole) {
        appRoleRepo.save(appRole);
    }

    @Override
    public void remove(Long id) {
        appRoleRepo.deleteById(id);
    }
}
