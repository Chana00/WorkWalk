package cap.workwalk.controller;

import cap.workwalk.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {



    @GetMapping("/search/walker")
    public String signUp(Model model) {
       // model.addAttribute();
        return "walkersearch";
    }
}
