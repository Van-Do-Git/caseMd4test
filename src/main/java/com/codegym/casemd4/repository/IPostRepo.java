package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.Account;
import com.codegym.casemd4.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPostRepo extends JpaRepository<Post,Long> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findPostByPrivacyContaining(String s, Pageable pageable);
    List<Post> findAllByAccount_IdAndPrivacyIsNotContaining(Long idAcc, String privacy);
    List<Post> findAllByAccount_Id(Long idAcc);
}
