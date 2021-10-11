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
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @GetMapping("/findPost/{idPost}")
    public ResponseEntity<Post> findPostById(@PathVariable("idPost") Long idPost) {
        Post post = servicePost.findById(idPost).get();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/timeline")
    public ResponseEntity<Page<Post>> timeline(@RequestBody String page) {
        String[] sortById = new String[2];
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 5, Sort.by("id").descending());
        Page<Post> postPage = servicePost.findPostByPrivacyContaining("public", pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @GetMapping("/likeshow/{idAcc}/{idPost}")
    public ResponseEntity<?> createlike(@PathVariable("idAcc") Long idAcc, @PathVariable("idPost") Long idPost) {
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
        return new ResponseEntity<>(HttpStatus.OK);
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
        Account account = serviceAccount.findById(idAcc).get();
        Account friend = serviceAccount.findById(idFriend).get();
        Friend friend1 = serviceFriend.findByAccount_IdAndAccount_Id(account, friend);
        Friend friend2 = serviceFriend.findByAccount_IdAndAccount_Id(friend, account);
        if (friend1 == null && friend2 == null) {
            Friend newFriend = new Friend();
            newFriend.setAccount(account);
            newFriend.setFriend(friend);
            newFriend.setStatus(false);
            serviceFriend.save(newFriend);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("exits", HttpStatus.OK);
    }

    @GetMapping("/showfriend/{idAcc}")
    public ResponseEntity<List<Account>> showListFriend(@PathVariable("idAcc") Long idAcc) {
        Account account = serviceAccount.findById(idAcc).get();
        List<Friend> list = serviceFriend.findAllByIdAcc(account, true, account, true);
        List<Account> accountList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAccount().getId() == idAcc) {
                    accountList.add(list.get(i).getFriend());
                } else {
                    accountList.add(list.get(i).getAccount());
                }
            }
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @GetMapping("/showrequestfriend/{idAcc}")
    public ResponseEntity<List<Account>> showRequestFriend(@PathVariable("idAcc") Long idAcc) {
        Account account = serviceAccount.findById(idAcc).get();
        List<Friend> list = serviceFriend.findFriendByIdAcc(account, false);
        List<Account> accountList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                accountList.add(list.get(i).getAccount());
            }
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @GetMapping("/confirmfriend/{idAcc}/{idFriend}")
    public ResponseEntity<String> confirmFriend(@PathVariable("idAcc") Long idAcc, @PathVariable("idFriend") Long idFriend) {
        Account account = serviceAccount.findById(idAcc).get();
        Account friend = serviceAccount.findById(idFriend).get();
        Friend friend1 = serviceFriend.findByAccount_IdAndAccount_Id(account, friend);
        Friend friend2 = serviceFriend.findByAccount_IdAndAccount_Id(friend, account);
        if (friend1 != null) {
            friend1.setStatus(true);
            serviceFriend.save(friend1);
        } else {
            friend2.setStatus(true);
            serviceFriend.save(friend2);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/showpostfriend/{idFriend}")
    public ResponseEntity<List<Post>> showPostFriend(@PathVariable("idFriend") Long idFriend) {
        List<Post> postList = servicePost.findAllByAccount_IdAndPrivacyIsNotContaining(idFriend, "onlyme");
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/showuserdetail/{idAcc}")
    public ResponseEntity<List<Post>> showUserDetail(@PathVariable("idAcc") Long idAcc) {
        List<Post> postList = servicePost.findAllByAccount_Id(idAcc);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @PutMapping("/reloadAvatar/{idAcc}")
    public ResponseEntity<String> reloadAvatar(@PathVariable("idAcc") Long idAcc, @RequestBody Image avatar) {
        Account account = serviceAccount.findById(idAcc).get();
        Post newPost = new Post();
        newPost.setConten("Đã thay ảnh đại diện");
        newPost.setTimePost(new Date());
        newPost.setPrivacy("public");
        newPost.setAccount(account);
        Post post = servicePost.add(newPost);
        Image newAvatar = serviceImage.add(avatar);
        newAvatar.setPost(post);
        account.setAvatar(newAvatar);
        serviceAccount.save(account);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/deletepost/{idPost}")
    public ResponseEntity<String> deletePost(@PathVariable Long idPost) {
        servicePost.remove(idPost);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @DeleteMapping("/refuse/{idAcc}/{idFriend}")
    public ResponseEntity<String> refuseFriend(@PathVariable("idAcc") Long idAcc, @PathVariable("idFriend") Long idFriend) {
        Account account = serviceAccount.findById(idAcc).get();
        Account friend = serviceAccount.findById(idFriend).get();
        Friend f = serviceFriend.findByAccount_IdAndAccount_Id(friend, account);
        serviceFriend.remove(f.getId());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
