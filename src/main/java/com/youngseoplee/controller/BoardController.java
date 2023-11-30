package com.youngseoplee.controller;

import com.youngseoplee.model.Post;
import com.youngseoplee.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    private final PostService postService;

    @Autowired
    public BoardController(PostService postService) {
        this.postService = postService;
    }

    // 처음 페이지 출력 시 모든 게시글을 가져옴.
    @GetMapping("/board")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts(); // 모든 게시글을 가져옵니다.
        model.addAttribute("posts", posts); // 모델에 게시글 목록을 추가합니다.
        return "board";
    }

    // 제목 클릭 시 세부내용 조회
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/posts/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "createPost";
    }

    // 게시글 작성
    @PostMapping("/posts")
    public String addPost(@ModelAttribute Post post) {
        System.out.println("[BoardController] call addPost & check post " + post);
        postService.savePost(post);
        return "redirect:/board";
    }

    // 게시글 편집
    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/posts/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        postService.updatePost(id, post);
        return "redirect:/board";
    }

    // 게시글 삭제
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/board";
    }


}
