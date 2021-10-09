package com.codegym.casemd4.service.post;


import com.codegym.casemd4.model.Post;
import com.codegym.casemd4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IServicePost extends IGeneralService<Post> {
    Post add(Post post);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findPostByPrivacyContaining(String optional, Pageable pageable);
}
