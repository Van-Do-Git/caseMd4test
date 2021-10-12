package com.codegym.casemd4.repository;

import com.codegym.casemd4.model.VerifiAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVerifiacc extends JpaRepository<VerifiAccount,Long> {
}
