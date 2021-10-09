package com.codegym.casemd4.controller;


import com.codegym.casemd4.model.*;
import com.codegym.casemd4.service.account.IServiceAccount;
import com.codegym.casemd4.service.accountlike.IServiceLike;
import com.codegym.casemd4.service.comment.IServiceComment;
import com.codegym.casemd4.service.friend.IServiceFriend;
import com.codegym.casemd4.service.image.IServiceImage;
import com.codegym.casemd4.service.jwt.JwtService;
import com.codegym.casemd4.service.post.IServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@EnableSpringDataWebSupport
public class AccountController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    IServiceImage serviceImage;
    @Autowired
    IServicePost servicePost;
    @Autowired
    IServiceLike serviceLike;
    @Autowired
    IServiceComment serviceComment;
    @Autowired
    JwtService jwtService;
    @Autowired
    IServiceFriend serviceFriend;


    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody Image image) {
        Post post = image.getPost();
        Long idAc = post.getAccount().getId();
        Account account = serviceAccount.findById(idAc).get();
        post.setAccount(account);

        Post newPost = servicePost.add(post);
        image.setPost(newPost);
        serviceImage.save(image);
        return new ResponseEntity<>(servicePost.findAll(), HttpStatus.OK);
    }

    @PostMapping("/timeline")
    public ResponseEntity<Page<Post>> timeline(@RequestBody String page) {
        String[] sortById = new String[2];
        sortById[0] = "id";
        sortById[0] = "desc";
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 20, Sort.by("id").descending());
        Page<Post> postPage = servicePost.findAll(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @GetMapping("/test{page}")
    public ResponseEntity<Page<Post>> test(@PageableDefault(size = 12) Pageable page, String s) {
        Page<Post> postPage = servicePost.findPostByPrivacyContaining(s, page);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @GetMapping("/likeshow/{idAcc}/{idPost}")
    public ResponseEntity<Post> createlike(@PathVariable("idAcc") Long idAcc, @PathVariable("idPost") Long idPost) {
        AccountLike accountLike = serviceLike.findByAccount_IdAndPost_Id(idAcc, idPost);
        if (accountLike != null) {
            Long idlike = accountLike.getId();
            serviceLike.remove(idlike);
        } else {
            Account account = serviceAccount.findById(idAcc).get();
            Post post = servicePost.findById(idPost).get();
            AccountLike accountLike1 = new AccountLike();
            accountLike1.setAccount(account);
            accountLike1.setPost(post);
            serviceLike.save(accountLike1);
        }
        serviceLike.remove(accountLike.getId());
        Post postNew = servicePost.findById(idPost).get();
        return new ResponseEntity<>(postNew, HttpStatus.OK);
    }

    @PostMapping("/comment/{idAcc}/{idPost}")
    public ResponseEntity<String> createComment(@RequestBody Comment comment, @PathVariable("idAcc") Long idAcc, @PathVariable("idPost") Long idPost) {
        Account account = serviceAccount.findById(idAcc).get();
        Post post = servicePost.findById(idPost).get();
        comment.setAccount(account);
        comment.setPost(post);
        serviceComment.save(comment);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping("/deletecomment/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable("idComment") Long idComment) {
        serviceComment.remove(idComment);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PostMapping("/searchfriend")
    public ResponseEntity<Account> searchFriend(@RequestBody Account account) {
        Account account1 = serviceAccount.loadUserByEmail(account.getEmail());
        if (account1 != null) {
            return new ResponseEntity<>(account1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sendaddfriend/{idAcc}/{idFriend}")
    public ResponseEntity<String> sendAddFriend(@PathVariable("idAcc") Long idAcc, @PathVariable("idFriend") Long idFriend) {
        Friend friend = serviceFriend.findByAccount_IdAndAccount_Id(idAcc, idFriend);
        Friend account = serviceFriend.findByAccount_IdAndAccount_Id(idFriend, idAcc);
        if (friend == null && account == null) {
            Account account1 = serviceAccount.findById(idAcc).get();
            Account account2 = serviceAccount.findById(idFriend).get();
            Friend newFriend = new Friend();
            newFriend.setAccount(account1);
            newFriend.setFriend(account2);
            newFriend.setStatus(false);
            serviceFriend.save(newFriend);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("exits", HttpStatus.OK);
    }

}
