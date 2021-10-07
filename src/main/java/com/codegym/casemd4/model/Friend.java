package com.codegym.casemd4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account id_account;

    @ManyToOne
    private Account id_friend;

    private Boolean status;

}
