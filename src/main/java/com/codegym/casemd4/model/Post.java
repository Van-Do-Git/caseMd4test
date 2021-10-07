package com.codegym.casemd4.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String privacy;
    private Date timePost;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "post")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> imageList;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "post")
    @LazyCollection (LazyCollectionOption.FALSE)
    private List<AccountLike> likeList;

    @OneToMany (cascade = CascadeType.ALL,mappedBy = "post")
    @LazyCollection (LazyCollectionOption.FALSE)
    private List<Comment> commentList;

    @ManyToOne
    private Account account;

}
