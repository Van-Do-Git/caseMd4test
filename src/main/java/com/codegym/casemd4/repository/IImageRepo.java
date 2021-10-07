package com.codegym.casemd4.repository;


import com.codegym.casemd4.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepo extends JpaRepository<Image,Long> {
}
