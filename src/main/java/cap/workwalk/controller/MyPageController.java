package cap.workwalk.controller;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.Reservation;
import cap.workwalk.entity.Role;
import cap.workwalk.entity.User;
import cap.workwalk.repository.ReservationRepository;
import cap.workwalk.repository.UserRepository;
import cap.workwalk.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MyPageController {
    @Autowired
    CustomUserService customUserService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/mypage")
    public String signUp(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));
        List<Reservation> rese = reservationRepository.findMyReservationById(user.getId());

        model.addAttribute("pet", new Pet());
        model.addAttribute("user", user);
        model.addAttribute("reseList", rese);
        return "mypage";
    }

    @PostMapping("/mypage")
    public String mypagePost(@ModelAttribute("pet") Pet pet, Principal principal) {
        customUserService.registrationPet(principal.getName(), pet);
       // System.out.println("새 반려견 등록... " +
        //        pet.getName() + pet.getSex() + pet.getSize() + pet.getBirth() + pet.getKind());
        return "redirect:/mypage";
    }

    @PostMapping("/mypage/upload")
    public String mypageUploadPost(
            @Valid @RequestParam("uploadImg") MultipartFile uploadImg, Principal principal, Model model)
    throws IOException, IllegalArgumentException {
        if(!uploadImg.isEmpty()) {
            customUserService.profileImgSave(uploadImg, principal);
        }

        return "redirect:/mypage";
    }




}
