package com.codegym.casemd4.service.post;


import com.codegym.casemd4.model.Post;
import com.codegym.casemd4.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePost implements IServicePost {
    @Autowired
    private IPostRepo postRepo;

    @Override
    public Iterable<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepo.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepo.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public List<Post> findAllByAccount_Id(Long idAcc) {
        return postRepo.findAllByAccount_Id(idAcc);
    }

    public Post add(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public Page<Post> findPostByPrivacyContaining(String s, Pageable pageable) {
        return postRepo.findPostByPrivacyContaining(s, pageable);
    }

    @Override
    public List<Post> findAllByAccount_IdAndPrivacyIsNotContaining(Long idAcc, String privacy) {
        return postRepo.findAllByAccount_IdAndPrivacyIsNotContaining(idAcc, privacy);
    }
}
