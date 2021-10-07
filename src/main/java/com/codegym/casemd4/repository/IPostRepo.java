package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post,Long> {
}
