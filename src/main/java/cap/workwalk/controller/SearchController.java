package cap.workwalk.controller;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/search/puppy")
    public String searchPuppy(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));

        //List<User> sameAddressUserList = userRepository.findDongAddress(user.getSearchAddress());

        List<Pet> sameAddressPetList = petRepository.findListByAddress(user.getSearchAddress());
        System.out.println(" 같은동 펫 리스트 : " + sameAddressPetList);

        model.addAttribute("userAddrDong", user.getSearchAddress());
        model.addAttribute("userAddr", user.getAddress());
        //model.addAttribute("sameAddressUserList", sameAddressUserList);
        model.addAttribute("sameAddressPetList", sameAddressPetList);
        return "searchs/puppysearch";
    }

    @GetMapping("/search/walker")
    public String searchWalker(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));


        List<User> sameAddressUserList = userRepository.findListByAddress(user.getSearchAddress());
        System.out.println(" 같은동 펫 리스트 : " + sameAddressUserList);

        model.addAttribute("userAddrDong", user.getSearchAddress());
        model.addAttribute("userAddr", user.getAddress());
        //model.addAttribute("sameAddressUserList", sameAddressUserList);
        model.addAttribute("sameAddressUserList", sameAddressUserList);
        return "searchs/walkersearch";
    }


}
