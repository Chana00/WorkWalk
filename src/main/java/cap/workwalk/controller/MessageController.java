package cap.workwalk.controller;

import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.ChatDto;
import cap.workwalk.entity.Chat;
import cap.workwalk.entity.Post;
import cap.workwalk.entity.User;
import cap.workwalk.repository.ChatRepository;
import cap.workwalk.repository.PostRepository;
import cap.workwalk.repository.UserRepository;
import cap.workwalk.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MessageController {

    final ChatService chatService;
    final ChatRepository chatRepository;
    final PostRepository postRepository;
    final UserRepository userRepository;

    @GetMapping("/message/list") //쪽지함
    public String messageList(Model model, @AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter,
                              @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        
        //페이징처리
        Page<Chat> receives = chatService.findByReceive(userDetailsAdapter.getUser(), pageable);
        int startPage = Math.max(1, receives.getPageable().getPageNumber() - 4);
        int endPage = Math.min(receives.getTotalPages(), receives.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        model.addAttribute("receivelist", chatService.findByReceive(userDetailsAdapter.getUser(),pageable));

        return "/message/list";



    }


    @GetMapping("/message/sendList") // 받은 쪽지함
    public String sendList(Model model, @AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
    {

        //페이징처리
        Page<Chat> sends = chatService.findBySend(userDetailsAdapter.getUser(), pageable);
        int startPage = Math.max(1, sends.getPageable().getPageNumber() - 4);
        int endPage = Math.min(sends.getTotalPages(), sends.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sendlist", chatService.findBySend(userDetailsAdapter.getUser(),pageable));

        return "/message/sendList";
    }

    @GetMapping("/message/write/{id}") //게시글에서 1:1쪽지 보내기
    public String message(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model, @PathVariable Integer id) {
        User senduser = userDetailsAdapter.getUser(); //본인
        Post receiveuser = postRepository.findById(id).get();

            model.addAttribute("senduser", senduser);
            model.addAttribute("receiveuser", receiveuser);
            return "/message/write";
        }

    @PostMapping("/message/write") //쪽지 보내기
    public String messageSave(ChatDto chatDto) {
        chatService.savePost(chatDto);
        return "redirect:/message/list";
    }

    @GetMapping("/message/detail/{id}") // 쪽지 상세보기
    public String messageDetail(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model, @PathVariable Integer id) {
        model.addAttribute("messagedetail",chatRepository.findById(id).get());
        model.addAttribute("user",userDetailsAdapter.getUser());
        return "/message/detail";
    }

}
