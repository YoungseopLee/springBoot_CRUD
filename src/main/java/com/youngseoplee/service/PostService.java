package com.youngseoplee.service;

import com.youngseoplee.model.Post;
import com.youngseoplee.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 저장
    public Post savePost(Post post) {
        System.out.println("[PostService] call savePost & check post " + post);
        post.setEditTime(LocalDateTime.now());
        return postRepository.save(post);
    }

    // 게시글 조회
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // 게시글 업데이트
    public Post updatePost(Long id, Post updatedPost) {
        Post existingPost = postRepository.findById(id).orElse(null);

        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setAuthor(updatedPost.getAuthor());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setEditTime(LocalDateTime.now());

            return postRepository.save(existingPost);
        }
        return null;
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        System.out.println("call deletePost!!!");
        postRepository.deleteById(id);
    }


}
