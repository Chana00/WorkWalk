package cap.workwalk.controller;

import cap.workwalk.entity.User;
import cap.workwalk.repository.UserRepository;
import cap.workwalk.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search/walker")
    public String signUp(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));
        model.addAttribute("userAddr", user.getSearchAddress());
        System.out.println(user.getSearchAddress());
        return "walkersearch";
    }
}
