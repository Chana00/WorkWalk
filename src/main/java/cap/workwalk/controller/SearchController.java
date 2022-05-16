package cap.workwalk.controller;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
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
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/search/walker")
    public String signUp(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));

        List<User> sameAddressUserList = userRepository.findDongAddress(user.getSearchAddress());
        List<Pet> sameAddressPetList = petRepository.findListByAddress(user.getSearchAddress());

        model.addAttribute("userAddrDong", user.getSearchAddress());
        model.addAttribute("userAddr", user.getAddress());
        model.addAttribute("sameAddressUserList", sameAddressUserList);
        model.addAttribute("sameAddressPetList", sameAddressPetList);
        return "walkersearch";
    }


}
