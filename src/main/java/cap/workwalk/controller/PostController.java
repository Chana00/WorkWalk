package cap.workwalk.controller;


import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.PostDto;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.repository.PostRepository;
import cap.workwalk.service.PostService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PetRepository petRepository;
    private final PostRepository postRepository;


    @GetMapping("/{posttype}/list") //게시판 목록
    public String Postlist(@PathVariable String posttype, Model model) {
        if (Objects.equals(posttype, "work")){
            model.addAttribute("worklist", postService.findByPosttype("work"));
            return "posts/work/list";
        }
        else if (Objects.equals(posttype, "walk")){
            model.addAttribute("walklist", postService.findByPosttype("walk"));
            return "posts/walk/list";
        }
        return "";
    }


    @GetMapping("/{posttype}/write") //로그인 된 user 데이터를 게시글 작성시 자동으로 불러오기
    public String Write(@PathVariable String posttype, @AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model) {
        if(userDetailsAdapter != null) {
            model.addAttribute("userdetail",userDetailsAdapter.getUser());
            model.addAttribute("petdetail", petRepository.findByUser(userDetailsAdapter.getUser()));
        }
        if(Objects.equals(posttype, "work")) {return "posts/work/write";}
        else if(Objects.equals(posttype, "walk")) {return "posts/walk/write";}
        return "";
    }


    @PostMapping("/write") //게시글 등록
    public String Save(PostDto postDto) {
        postService.savePost(postDto);
        if (Objects.equals(postDto.getPosttype(), "work")) {
            return "redirect:/posts/work/list";
        } else if (Objects.equals(postDto.getPosttype(), "walk")) {
            return "redirect:/posts/walk/list";
        }
        return "";
    }

    @GetMapping("/{posttype}/{id}") //게시글 상세조회
    public String Detail(@PathVariable String posttype, @PathVariable Integer id,@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model){
        model.addAttribute("postdetail", postRepository.findByPosttypeAndId(posttype,id));
        model.addAttribute("userdetail",userDetailsAdapter.getUser());
        return "posts/detail";
    }

    @GetMapping("/{posttype}/edit/{id}") //게시글 수정
    public String Edit(@PathVariable String posttype, @PathVariable Integer id, Model model){
        model.addAttribute("postdetail",postRepository.findByPosttypeAndId(posttype,id));
        return "posts/edit";
    }

    @PostMapping("/{posttype}/edit")
    public String Update(PostDto postDto) {
        postService.savePost(postDto);
        if (Objects.equals(postDto.getPosttype(), "work")) {
            return "redirect:/posts/work/list";
        } else if (Objects.equals(postDto.getPosttype(), "walk")) {
            return "redirect:/posts/walk/list";
        }
        return "";
    }

}