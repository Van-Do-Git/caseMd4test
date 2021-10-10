package com.codegym.casemd4.service.post;


import com.codegym.casemd4.model.Post;
import com.codegym.casemd4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IServicePost extends IGeneralService<Post> {
    List<Post> findAllByAccount_Id(Long idAcc);
    Post add(Post post);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findPostByPrivacyContaining(String optional, Pageable pageable);
    List<Post> findAllByAccount_IdAndPrivacyIsNotContaining(Long idAcc, String privacy);
}
