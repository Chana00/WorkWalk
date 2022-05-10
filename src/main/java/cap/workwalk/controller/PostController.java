package cap.workwalk.controller;


import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.PostDto;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.service.PostService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PetRepository petRepository;

    @GetMapping("/posts/work/list")
    public String Work(Model model) {
        model.addAttribute("worklist", postService.findPosttypeWork());
        return "posts/work/list";
    }

    @GetMapping("/posts/walk/list")
    public String Walk(Model model) {
        model.addAttribute("walklist", postService.findPosttypeWalk());
        return "posts/walk/list";
    }

    @GetMapping("/posts/walk/write") //로그인 된 user 데이터를 게시글 작성시 자동으로 불러오기
    public String walkwrite(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model) {
        if(userDetailsAdapter != null) {
            model.addAttribute("userdetail",userDetailsAdapter.getUser());
            model.addAttribute("petdetail", petRepository.findByUser(userDetailsAdapter.getUser()));
        }
        return "posts/walk/write";
    }

    @PostMapping("/posts/walk/write")
    public String walksave(PostDto postDto) {
        postService.savePost(postDto);
        return "redirect:/posts/walk/list";
    }

    @PostMapping("/posts/work/write")
    public String worksave(PostDto postDto) {
        postService.savePost(postDto);
        return "redirect:/posts/work/list";
    }

    @GetMapping("/posts/work/write") //로그인 된 user 데이터를 게시글 작성시 자동으로 불러오기
    public String workwrite(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model) {
        if(userDetailsAdapter != null) {
            model.addAttribute("userdetail",userDetailsAdapter.getUser());
            model.addAttribute("petdetail", petRepository.findByUser(userDetailsAdapter.getUser()));
        }
        return "posts/work/write";
    }

    @GetMapping("/posts/work/{id}")
    public String findByIdWork(@PathVariable Integer id, Model model){
        model.addAttribute("postdetail",postService.findById(id));
        return "posts/detail";
    }

    @GetMapping("/posts/walk/{id}")
    public String findByIdWalk(@PathVariable Integer id, Model model){
        model.addAttribute("postdetail",postService.findById(id));
        return "posts/detail";
    }
}