package cap.workwalk.controller;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.Role;
import cap.workwalk.entity.User;
import cap.workwalk.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyPageController {
    @Autowired
    CustomUserService customUserService;

    @GetMapping("/mypage")
    public String signUp(Model model) {
        model.addAttribute("pet", new Pet());
        return "mypage";
    }

    @PostMapping("/mypage")
    public String mypagePost(@ModelAttribute("pet") Pet pet, Principal principal) {
        customUserService.registrationPet(principal.getName(), pet);
        System.out.println("새 반려견 등록... " +
                pet.getName() + pet.getSex() + pet.getSize() + pet.getBirth() + pet.getKind());
        return "redirect:/mypage";
    }




}
