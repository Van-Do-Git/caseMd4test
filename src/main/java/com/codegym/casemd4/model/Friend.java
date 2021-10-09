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
    private Account account;

    @ManyToOne
    private Account friend;

    private Boolean status;

}
