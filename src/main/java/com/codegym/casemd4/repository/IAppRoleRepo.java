package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends JpaRepository<AppRole,Long> {
}
