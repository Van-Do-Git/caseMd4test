package com.codegym.casemd4.dto;

import com.codegym.casemd4.model.Image;
import lombok.Data;

@Data
public class LoginAccount {
    private Long id;
    private String fullName;
    private Image avatar;
    private String token;
}