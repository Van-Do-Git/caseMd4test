package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {
}
