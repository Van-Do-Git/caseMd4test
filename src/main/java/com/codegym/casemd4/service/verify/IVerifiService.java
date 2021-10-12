package com.codegym.casemd4.service.verify;

import com.codegym.casemd4.model.VerifiAccount;
import com.codegym.casemd4.service.IGeneralService;

public interface IVerifiService extends IGeneralService<VerifiAccount> {
    VerifiAccount add(VerifiAccount verifiAccount);
}
