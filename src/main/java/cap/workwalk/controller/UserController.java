package cap.workwalk.controller;

import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.PetDto;
import cap.workwalk.entity.Role;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.service.CustomUserService;
import cap.workwalk.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    CustomUserService customUserService;

    private final PetRepository petRepository;
    private final PetService petService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpPost(@ModelAttribute("user") User user) {

            Role role = customUserService.findByRolename("USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(role);

            customUserService.signUpUser(user, userRoles);
            return "redirect:/";

    }


    @PostMapping("/pet") //반려견 등록
    public String save(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, PetDto petDto) {
        petService.petSave(userDetailsAdapter.getUser().getId(), petDto);
        return "redirect:/users/mypage2";
    }

    @GetMapping("/pet")
    public String pet(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, Model model) {
        if (userDetailsAdapter != null) {
            model.addAttribute("user", userDetailsAdapter.getUser());
        }
        return "pet";
    }




}
