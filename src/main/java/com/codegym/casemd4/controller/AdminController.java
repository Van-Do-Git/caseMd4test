package com.codegym.casemd4.controller;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.service.account.IServiceAccount;
import com.codegym.casemd4.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    JwtService jwtService;


}
