package cap.workwalk.controller;


import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.PostDto;
import cap.workwalk.entity.Pet;
import cap.workwalk.entity.Post;
import cap.workwalk.entity.Reservation;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.repository.PostRepository;
import cap.workwalk.service.PostService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String Postlist(@PathVariable String posttype, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> workposts = postService.findByPosttype("work", pageable);
        Page<Post> walkposts = postService.findByPosttype("walk", pageable);

        int startPage = Math.max(1, workposts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(workposts.getTotalPages(), workposts.getPageable().getPageNumber() + 4);

        int startPage2 = Math.max(1, walkposts.getPageable().getPageNumber() - 4);
        int endPage2 = Math.min(walkposts.getTotalPages(), walkposts.getPageable().getPageNumber() + 4);

        if (Objects.equals(posttype, "work")){
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("worklist", postService.findByPosttype("work", pageable));
            return "posts/work/list";
        }
        else if (Objects.equals(posttype, "walk")){
            model.addAttribute("startPage2", startPage2);
            model.addAttribute("endPage2", endPage2);
            model.addAttribute("walklist", postService.findByPosttype("walk", pageable));
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
        model.addAttribute("reservation", new Reservation());
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

    @PostMapping("/reservation")
    public String userReservation(@ModelAttribute("reservation") Reservation newReservation){
        System.out.println(newReservation.getMy_id() + " + " + newReservation.getPost_id() + " + " + newReservation.getOther_id());
        postService.saveReservation(newReservation);
        return "redirect:/mypage";
    }

    @DeleteMapping("/{posttype}/{id}")
    public String Delete(@PathVariable String posttype, @PathVariable Integer id, PostDto postDto){
        postService.deletePost(postDto);
        if (Objects.equals(postDto.getPosttype(), "work")) {
            return "redirect:/posts/work/list";
        } else if (Objects.equals(postDto.getPosttype(), "walk")) {
            return "redirect:/posts/walk/list";
        }
        return "";
    }

}