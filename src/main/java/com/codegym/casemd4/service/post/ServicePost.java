package com.codegym.casemd4.service.post;


import com.codegym.casemd4.model.Post;
import com.codegym.casemd4.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePost implements IServicePost{
    @Autowired
    private IPostRepo postRepo;
    @Override
    public Iterable<Post> findAll() {
        return postRepo.findAll() ;
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
}
